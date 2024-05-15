/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageChannel {

    private Map<String, String> channelMap = new HashMap<>(); // Mappe les paires apprenant-formateur aux canaux de messagerie

    public synchronized String getChannelForPair(int apprenantId, int formateurId) {
        String channelKey = apprenantId + "-" + formateurId; // Clé unique pour la paire apprenant-formateur

        if (!channelMap.containsKey(channelKey)) {
            // Créer un nouveau canal de messagerie si la clé n'existe pas
            String channelName = generateChannelName();
            channelMap.put(channelKey, channelName);
            return channelName;
        } else {
            // Renvoyer le canal de messagerie existant
            return channelMap.get(channelKey);
        }
    }

    private String generateChannelName() {
        // Logique pour générer un nom de canal unique (par exemple, UUID)
        return UUID.randomUUID().toString();
    }
}
