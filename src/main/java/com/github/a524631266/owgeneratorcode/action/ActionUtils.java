package com.github.a524631266.owgeneratorcode.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;

public class ActionUtils {
    public static void notify(String notifyContent) {
        NotificationGroup notificationGroup = new NotificationGroup("ow_generator_code", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification(notifyContent , MessageType.INFO);
        Notifications.Bus.notify(notification);
    }
}
