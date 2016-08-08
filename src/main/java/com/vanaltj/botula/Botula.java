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

package com.vanaltj.botula;

import java.io.IOException;
import java.util.Collection;

import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;

import com.vanaltj.botula.config.Configurations;

public class Botula {

    public static void main(String[] args) {
        Collection<Configuration> configs = null;
        MultiBotManager bots = new MultiBotManager();
        try {
            configs = Configurations.getBotConfigs();
        } catch (IOException ioe) {
            noConfigs(ioe);
        }
        if (configs == null) {
            noConfigs(new RuntimeException("Configs returned null."));
        }
        if (configs.isEmpty()) {
            noConfigs (new RuntimeException("Configs returned empty."));
        }
        for (Configuration conf : configs) {
            bots.addNetwork(conf);
        }
        bots.start();
    }

    private static void noConfigs(Exception ioe) {
        System.out.println("Dang, no configurations?\n");
        ioe.printStackTrace();
        System.out.println("\n\nGiving up now.");
        System.exit(-1);
    }
}
