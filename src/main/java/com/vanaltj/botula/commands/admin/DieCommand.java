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

package com.vanaltj.botula.commands.admin;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class DieCommand extends AdminCommand {

    private static final String TRIGGER = "die";

    @Override
    public void run(String[] args, PrivateMessageEvent event) {

        event.respond("OK I quit now.");
        PircBotX bot = event.getBot();
        bot.sendIRC().quitServer("So long and thanks for all the fish!");
        System.exit(0);

    }

    @Override
    public String getTrigger() {
        return TRIGGER;
    }

}
