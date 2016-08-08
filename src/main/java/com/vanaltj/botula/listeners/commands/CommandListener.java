/*
 * Copyright 2012-2016 Jon VanAlten
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

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import com.vanaltj.botula.commands.Command;

public abstract class CommandListener extends ListenerAdapter {

    private Class<Command<GenericMessageEvent>> commandClass;
    protected Map<String, Command<GenericMessageEvent>> commands;

    @SuppressWarnings("unchecked")
    public CommandListener(Class<?> commandClass) {
        this.commandClass = (Class<Command<GenericMessageEvent>>) commandClass;
        initCommands();
    }

    private void initCommands() {
        commands = new HashMap<>();
        ServiceLoader<Command<GenericMessageEvent>> cmds = ServiceLoader.load(commandClass, getClass().getClassLoader());
        if (!cmds.iterator().hasNext()) {
            System.out.println("No commands of type: " + commandClass);
        }
        for (Command<GenericMessageEvent> cmd : cmds) {
            System.out.println("Loading command: " + cmd.getTrigger());
            commands.put(cmd.getTrigger(), cmd);
        }
    }

    protected void runCommand(GenericMessageEvent event) {
        String[] commandParts = event.getMessage().split(" ");
        if (commandParts.length > 0) {
            Command<GenericMessageEvent> cmd = commands.get(commandParts[0].trim());
            if (cmd != null) {
                cmd.run(commandParts, event);
            }
        }
    }

}
