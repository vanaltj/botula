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

package com.vanaltj.botula.listeners.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import com.vanaltj.botula.commands.admin.AdminCommand;

public class AdminCommandListener extends CommandListener {

    private String admin;

    public AdminCommandListener(String adminNick) {
        super(AdminCommand.class);
        admin = adminNick;
    }

    @Override
    public void onPrivateMessage(PrivateMessageEvent event) throws Exception {
        if (adminApproved(event)) {
            runCommand(event.getMessage().split(" "), event);
        }
    }

    private boolean adminApproved(PrivateMessageEvent event) {
        return event.getUser().getNick().equals(admin);
    }
}
