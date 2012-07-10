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

import java.util.Collection;

public class BotConfig {

    private String name;
    private String finger;
    private String adminNick;
    private String server;
    private final Collection<String> channels;

    public BotConfig(String name, String finger, String adminNick, String server,
            Collection<String> channels) {
        this.name = name;
        this.finger = finger;
        this.adminNick = adminNick;
        this.server = server;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public String getFinger() {
        return finger;
    }

    public String getAdminNick() {
        return adminNick;
    }

    public String getServer() {
        return server;
    }

    public Collection<String> getChannels() {
        return channels;
    }
}
