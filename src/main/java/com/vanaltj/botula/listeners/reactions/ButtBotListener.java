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

package com.vanaltj.botula.listeners.reactions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class ButtBotListener extends ListenerAdapter {

    private static final double BUTT_MESSAGE_RATIO = 0.07;
    private static final double BUTT_WORD_RATIO = 0.2;
    private static final String BUTT = "butt";
    
    private static final List<String> suffixes;
    private static final List<String> prefixes;
    
    static {
        Properties buttProps = new Properties();
        try {
            buttProps.load(ButtBotListener.class.getResourceAsStream("/butt.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        prefixes = getExploded(buttProps, "prefixes");
        suffixes = getExploded(buttProps, "suffixes");
    }
    
    private static final List<String> getExploded(Properties p, String propKey) {
        String prop = null;
        if (p != null) {
            prop = p.getProperty(propKey);
        }
        List<String> exploded = prop == null ? new ArrayList<>() : Arrays.asList(prop.split(","));
        Collections.sort(exploded);
        return Collections.unmodifiableList(exploded);
    }
    
    private final String botName;
    
    public ButtBotListener(String botName) {
        super();
        this.botName = botName;
    }
 
    @Override
    public void onMessage(MessageEvent event) throws Exception {
        if (botName.equals(event.getUser().getNick()) ||
                Math.abs(ThreadLocalRandom.current().nextDouble()) >= BUTT_MESSAGE_RATIO) {
            return;
        }
        StringBuilder butted = new StringBuilder();
        if (buttifyMessage(event.getMessage(), butted)) {
            event.respondChannel(butted.toString());
        }
    }
    
    private boolean buttifyMessage(String original, StringBuilder butted) {
        boolean didButtify = false;
        String[] messageWords = original.split(" ");
        if (buttifyWordMaybe(messageWords[0], butted)) {
            didButtify = true;
        }
        for (int i = 1; i < messageWords.length; i++) {
            butted.append(" ");
            if (buttifyWordMaybe(messageWords[i], butted)) {
                didButtify = true;
            }
        }
        return didButtify;
    }
    
    private boolean buttifyWordMaybe(String original, StringBuilder butted) {
        if (Math.abs(ThreadLocalRandom.current().nextDouble()) < BUTT_WORD_RATIO) {
            butted.append(buttifyWordActually(original));
            return true;
        } else {
            butted.append(original);
            return false;
        }
    }

    private String buttifyWordActually(String original) {
        StringBuilder b = new StringBuilder();
        for (String prefix : prefixes) {
            if (original.length() != prefix.length() && original.startsWith(prefix)) {
                b.append(prefix);
                break;
            }
        }
        // TODO syllable-ize and buttify a single syllable.
        b.append(BUTT);
        for (String suffix : suffixes) {
            if (original.length() != suffix.length() && original.endsWith(suffix)) {
                b.append(suffix);
                break;
            }
        }
        return b.toString();
    }

}
