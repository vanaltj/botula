/*
 * Copyright 2012 Jon VanAlten
 *
 * This file is part of Botula.
 *
 * Botula is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Botula is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Botula.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.vanaltj.botula;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.managers.ListenerManager;

import com.vanaltj.botula.listeners.commands.AdminCommandListener;
import com.vanaltj.botula.listeners.commands.ChannelCommandListener;

public class Botula {

    public static void main(String[] args) {
        PircBotX bot = new PircBotX();
        bot.setName("CountBotula");
        bot.setFinger("I'm Henry VIII I am.");
        ListenerManager<? extends PircBotX> lm = bot.getListenerManager();
        lm.addListener(new AdminCommandListener("vanaltj"));
        lm.addListener(new ChannelCommandListener());
        try {
            bot.connect("irc.example.com");
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }
        bot.joinChannel("#botula");
    }
}
