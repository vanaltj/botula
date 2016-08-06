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

package com.vanaltj.botula.commands.channel;

import org.pircbotx.hooks.events.MessageEvent;

public class EchoCommand extends ChannelCommand {

    public static final String TRIGGER = "!ping";

    @Override
    public void run(String[] args, MessageEvent event) {
        event.respond("Pong!");
    }

    @Override
    public String getTrigger() {
        return TRIGGER;
    }

}
