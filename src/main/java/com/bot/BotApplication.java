package com.bot;

import com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebServiceProxy;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.LineBotProperties;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Response;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.rmi.RemoteException;

@SpringBootApplication
@LineMessageHandler
public class BotApplication {

    @Value("${line.bot.channelToken}")
    private String channelToken;

    @Autowired
    private LineMessagingClient lineMessagingClient;

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);

        //test push message location

        LocationMessage locationMessage = new LocationMessage("suk sa wad", "khannatao", 13.8408249, 100.6618506);

        PushMessage pushMessage = new PushMessage(
                "line user id",
                locationMessage
        );
        try {

            Response<BotApiResponse> response =
                    LineMessagingServiceBuilder
                            .create("token")
                            .build()
                            .pushMessage(pushMessage)
                            .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (Exception e) {
            System.out.println(e);
        }


    }


    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        JtracDeliveryWebServiceProxy jt = new JtracDeliveryWebServiceProxy();

        Source source = event.getSource();

        if (source instanceof GroupSource) {

            GroupSource g = ((GroupSource) source);

            System.out.println("*****group*****:" + "user id:" + g.getUserId());
            System.out.println("*****group*****:" + "group id:" + g.getGroupId());
            System.out.println("*****group*****:" + "sender id:" + g.getSenderId());

        } else if (source instanceof RoomSource) {
            RoomSource r = ((RoomSource) source);

            System.out.println("*****room*****:" + "user id:" + r.getUserId());
            System.out.println("*****room*****:" + "room id:" + r.getRoomId());
            System.out.println("*****room*****:" + "sender id:" + r.getSenderId());

        } else {

            String userId = source.getUserId();
            String textMessage = event.getMessage().getText();

            try {

                System.out.println("*****private*****:" + "user id:" + userId);
                System.out.println("*****private*****:" + "text message:" + textMessage);
                jt.recivceMessage(userId, textMessage, channelToken);

            } catch (RemoteException e) {
                e.printStackTrace();
                System.out.println("*****private exception*****::" + e.getMessage());
            }
        }


    }

    //unfriend
    @EventMapping
    public void handleUnfollowEvent(UnfollowEvent event) {

        String userId = event.getSource().getUserId();
        addFriend(userId, false);
    }


    //add friend
    @EventMapping
    public void handleFollowEvent(FollowEvent event) {

        String userId = event.getSource().getUserId();
        addFriend(userId, true);

    }

    @EventMapping
    public void handleOtherEvent(Event event) {
        System.out.println("Received message(Ignored): {}" + event);
    }


    private void addFriend(String userId, boolean active) {
        Response<UserProfileResponse> response = null;

        if (userId != null) {

            try {
                response = LineMessagingServiceBuilder
                        .create(channelToken)
                        .build()
                        .getProfile(userId)
                        .execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (response.isSuccessful()) {

            try {

                UserProfileResponse profileResponse = response.body();
                String email = profileResponse.getStatusMessage();

                String newUserId = profileResponse.getUserId();

                if (isValidEmailAddress(email)) {
                    JtracDeliveryWebServiceProxy jt = new JtracDeliveryWebServiceProxy();
                    jt.addFriend(userId, email, active);
                }

                System.out.println("add Friend userId:" + userId);
                System.out.println("add Friend email:" + email);
                System.out.println("add Friend active:" + active);
                System.out.println("add Friend newUserId:" + newUserId);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}