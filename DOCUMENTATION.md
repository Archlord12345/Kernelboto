# Documentation Technique - Archbot AI Mod

Cette documentation décrit le fonctionnement interne du mod Archbot AI.

## Architecture de l'IA

L'intelligence de l'Archbot est basée sur une interaction périodique avec l'API Gemini de Google.

### Le "Prompt" de l'IA

Toutes les 5 secondes (100 ticks de jeu), le mod envoie une requête à l'API Gemini. Cette requête contient un "prompt" qui décrit l'état actuel de l'Archbot et de son environnement. Le prompt est structuré de la manière suivante :

```
"You are a Minecraft player named Archbot. Your goal is to survive and build a base. You can perform the following actions: wander, mine <block>, build <block>, attack <target>, craft <item>, communicate <message>. Your response must be in English. Based on the following state, what is your next action? [ÉTAT ACTUEL]"
```

L'état actuel inclut des informations telles que :

*   La santé de l'Archbot.
*   Sa position.
*   Les blocs et les entités à proximité.

### Les Actions de l'IA

L'API Gemini répond avec une chaîne de caractères qui représente la prochaine action de l'Archbot. Le mod parse ensuite cette chaîne et l'associe à un "Goal" (comportement) spécifique.

## Structure du Code

Le code du mod est organisé de la manière suivante :

*   `com.archbot` : Le package principal du mod.
    *   `ArchbotMod.java` : La classe principale du mod, responsable de l'initialisation.
*   `com.archbot.ai` : Contient la logique de l'IA.
    *   `GeminiAI.java` : Gère la communication avec l'API Gemini.
    *   `ArchbotAIEvents.java` : Gère le cycle de vie de l'IA et l'assignation des "Goals".
*   `com.archbot.entity` : Contient les classes des entités.
    *   `ArchbotEntity.java` : La classe de l'entité Archbot.
    *   `ModEntities.java` : Gère l'enregistrement des entités.
*   `com.archbot.goal` : Contient les classes des "Goals" personnalisés.
    *   `MineBlockGoal.java`
    *   `PlaceBlockGoal.java`
    *   `SmartAttackGoal.java`
    *   `CraftItemGoal.java`
*   `com.archbot.client` : Contient le code côté client.
    *   `ArchbotRenderer.java` : Gère le rendu de l'entité Archbot.
    *   `ClientEvents.java` : Gère l'enregistrement des "Renderers".
*   `com.archbot.event` : Contient les gestionnaires d'événements.
    *   `ModEvents.java` : Gère l'enregistrement des attributs des entités.
