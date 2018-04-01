package io;

import user.OnlineFriendsAndStatus;
import user.TimeBasedInformations;
import user.UserInformations;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        OnlineFriendsAndStatus onlineFriendsAndStatus1 = new OnlineFriendsAndStatus();
        onlineFriendsAndStatus1.setFriendUserID(1);
        onlineFriendsAndStatus1.setDeviceType("IOS");
        onlineFriendsAndStatus1.setStatus("idle");

        OnlineFriendsAndStatus onlineFriendsAndStatus2 = new OnlineFriendsAndStatus();
        onlineFriendsAndStatus2.setFriendUserID(2);
        onlineFriendsAndStatus2.setDeviceType("Android");
        onlineFriendsAndStatus2.setStatus("online");

        OnlineFriendsAndStatus onlineFriendsAndStatus3 = new OnlineFriendsAndStatus();
        onlineFriendsAndStatus3.setFriendUserID(3);
        onlineFriendsAndStatus3.setDeviceType("Android");
        onlineFriendsAndStatus3.setStatus("idle");

        ArrayList<OnlineFriendsAndStatus> onlineFriendsAndStatusArrayList = new ArrayList<>();
        onlineFriendsAndStatusArrayList.add(onlineFriendsAndStatus1);
        onlineFriendsAndStatusArrayList.add(onlineFriendsAndStatus2);
        onlineFriendsAndStatusArrayList.add(onlineFriendsAndStatus3);

        TimeBasedInformations timeBasedInformations = new TimeBasedInformations();
        timeBasedInformations.setActiveFriendsCount(1);
        String theDate = "05/20/2014 01:29:32 am";
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss a", Locale.US);
        try {
            Date date = format.parse(theDate);
            timeBasedInformations.setCurrentTimestamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeBasedInformations.setIdleFriendsCount(2);
        timeBasedInformations.setMobileActiveCount(1);
        timeBasedInformations.setMobileIdleCount(0);
        timeBasedInformations.setWebUsersCount(0);
        timeBasedInformations.setOnlineFriendsList(onlineFriendsAndStatusArrayList);

        TimeBasedInformations timeBasedInformations2 = new TimeBasedInformations();
        timeBasedInformations.setActiveFriendsCount(1);
        String theDate2 = "05/20/2014 02:29:32 am";
        SimpleDateFormat format2 = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss a", Locale.US);
        try {
            Date date = format2.parse(theDate);
            timeBasedInformations.setCurrentTimestamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeBasedInformations.setIdleFriendsCount(2);
        timeBasedInformations.setMobileActiveCount(1);
        timeBasedInformations.setMobileIdleCount(0);
        timeBasedInformations.setWebUsersCount(0);
        timeBasedInformations.setOnlineFriendsList(onlineFriendsAndStatusArrayList);

        ArrayList<TimeBasedInformations> timeBasedInformationsArrayList = new ArrayList<>();
        timeBasedInformationsArrayList.add(timeBasedInformations);
        timeBasedInformationsArrayList.add(timeBasedInformations2);
        UserInformations user1 = new UserInformations();
        user1.setUserId(4);
        user1.setUserActivites(timeBasedInformationsArrayList);

        System.out.println(user1.getUserActivites().get(1));
    }
}
