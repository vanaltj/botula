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

package com.vanaltj.botula.commands;

import org.pircbotx.hooks.Event;

public abstract class Command<E extends Event> {

    /**
     * Run the command.
     * 
     * @param args Arguments for the command.  These are taken right from the split
     *             message, so the first member of this array will be the command
     *             name.
     * @param event The event that this command is being invoked in response to.
     */
    public abstract void run(String[] args, E event);

    /**
     * Get this command's name.
     * 
     * @return the token which, when detected at beginning of line, will .
     */
    public abstract String getTrigger();

}
