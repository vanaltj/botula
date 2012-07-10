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
import java.util.Collection;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.managers.ListenerManager;

import com.vanaltj.botula.Configurations.MadeNewPropertiesException;
import com.vanaltj.botula.listeners.commands.AdminCommandListener;
import com.vanaltj.botula.listeners.commands.ChannelCommandListener;

public class Botula extends PircBotX {

    private String server;
    private Collection<String> channels;

    private Botula(BotConfig conf) {
        super();
        setName(conf.getName());
        String finger = conf.getFinger();
        if (finger != null) {
            setFinger(finger);
        }
        server = conf.getServer();
        this.channels = conf.getChannels();
        ListenerManager<? extends PircBotX> lm = getListenerManager();
        lm.addListener(new AdminCommandListener(conf.getAdminNick()));
        lm.addListener(new ChannelCommandListener());
    }

    private void connectAndJoinChannels() throws IOException, IrcException {
        connect(server);
        for (String channel : channels) {
            joinChannel(channel);
        }
    }

    public static void main(String[] args) {
        Collection<BotConfig> configs = null;
        try {
            configs = Configurations.getBotConfigs();
        } catch (MadeNewPropertiesException mnpe) {
            System.exit(0);
        } catch (IOException ioe) {
            System.out.println("Dang, can't even get configurations?\n");
            ioe.printStackTrace();
            System.out.println("\n\nGiving up now.");
            System.exit(-1);
        }
        for (BotConfig conf : configs) {
            Botula bot = new Botula(conf);
            try {
                bot.connectAndJoinChannels();
            } catch (IOException | IrcException e) {
                e.printStackTrace();
            }
        }
    }
}
