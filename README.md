# Archbot AI Mod

Ce mod pour Minecraft 1.20.1 ajoute une nouvelle entité nommée "Archbot", un mob doté d'une intelligence artificielle avancée grâce à l'API Gemini de Google. L'Archbot est conçu pour se comporter comme un joueur, capable d'interagir avec le monde de manière complexe.

## Fonctionnalités

*   **IA Avancée** : L'Archbot utilise l'API Gemini pour prendre des décisions. Il peut analyser son environnement et décider de manière autonome de ses actions.
*   **Comportements Complexes** : L'Archbot peut effectuer une variété d'actions, notamment :
    *   Miner des blocs.
    *   Construire des structures simples.
    *   Attaquer des monstres.
    *   Communiquer avec les joueurs.
*   **Personnalisation** : Le skin de l'Archbot peut être facilement modifié.

## Installation

1.  Assurez-vous d'avoir installé [Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html) pour la version 1.20.1 de Minecraft.
2.  Téléchargez la dernière version du mod depuis la section "Releases" de ce dépôt GitHub.
3.  Placez le fichier `.jar` du mod dans le dossier `mods` de votre installation de Minecraft.

## Configuration de l'API Gemini

Pour que l'IA de l'Archbot fonctionne, vous devez fournir votre propre clé API Gemini.

1.  Créez un fichier nommé `geminikey.txt` à la racine de votre dossier Minecraft (le même dossier où se trouve le dossier `mods`).
2.  Collez votre clé API Gemini dans ce fichier et enregistrez-le.

## Compilation (pour les développeurs)

Pour compiler le mod à partir des sources, exécutez la commande suivante à la racine du projet :

```bash
./gradlew build
```
