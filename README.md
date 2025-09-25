# FireBound

A fantasy-themed 2D scrolling game built in Java featuring a flame character named Fugi navigating through a dungeon environment.

## 🎮 Game Overview

FireBound tells the story of Fugi, a flame born inside a dungeon who must protect herself by throwing fire at slimy enemies. The game combines shooting mechanics, character growth, and survival elements in a challenging scrolling environment.

## 🎯 Objective

To win the game, players must achieve all three conditions:
- **Score**: Reach 2000 points
- **Targets**: Hit 4 special targets
- **Growth**: Achieve 100 growth units

The game ends in defeat if Fugi's HP reaches 0.

## 🕹️ Controls

- **Arrow Keys**: Move Fugi (Up, Down, Left, Right)
- **Spacebar**: Shoot projectiles (requires ammo)
- **Enter**: Advance past splash screen
- **P**: Pause/unpause game
- **D**: Toggle debug mode
- **+/-**: Increase/decrease game speed
- **Escape**: Quit game

## ⚡ Core Mechanics

### 1. Combat System
- Shoot fire projectiles at enemies and targets
- Ammo system limits consecutive shots
- Projectiles can destroy both enemies and obstacles

### 2. Growth System
- Consume logs and coal to grow in size and gain ammo
- Growth units track progression (max 100)
- Size decreases when hit by enemies
- Character appearance changes at 40 growth units

### 3. Dynamic Difficulty
- Entity speeds increase over time
- More frequent enemy spawns as game progresses
- Targets spawn rarely (every 1000 ticks) for added challenge

## 🎨 Entities

### Player Character
- **Fugi**: The flame protagonist with multiple visual states based on growth and damage

### Collectibles
- **Logs**: Basic collectible that provides points, ammo, and small growth
- **Coal**: Rare collectible that provides bonus ammo and significant growth

### Obstacles
- **Enemies**: Slimy creatures that damage the player and reduce growth
- **Targets**: Special rare entities that must be shot to win

### Projectiles
- **Fire Projectiles**: Player's ammunition for combat

## 🏗️ Architecture

The game is built using object-oriented programming principles with a clear class hierarchy:

### Core Classes
- **FireBound**: Main game class extending ScrollingGame
- **Fugi**: Player character class extending Player
- **Entity**: Base class for all game objects

### Interfaces
- **Consumable**: For entities that can be collected/consumed
- **Scrollable**: For entities that move with the game scroll

### Entity Hierarchy
```
Entity
├── Player
│   └── Fugi
├── Avoid
│   ├── Enemy
│   └── Target
├── Get
│   ├── Log
│   └── RareGet
│       └── Coal
└── Projectile
```

## 🎵 Assets

### Visual Assets
- Character sprites with multiple states (normal, damaged, grown)
- Enemy animations
- Dungeon background
- Various collectible sprites

### Audio
- Background music from intro.wav
- Sound effects integrated via PlayMusic class

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- All game assets in the `game_assets/` directory
- Sound files in the `sound_effects/` directory

### Running the Game
1. Compile all Java files
2. Run the Launcher class:
```bash
java Launcher
```

## 🎮 Game Features

- **Responsive Controls**: Smooth character movement and shooting
- **Visual Feedback**: Character appearance changes based on status
- **Progressive Difficulty**: Game becomes more challenging over time
- **Multiple Win Conditions**: Requires strategy across different gameplay elements
- **Debug Mode**: Developer tools for testing and debugging

## 📊 Scoring System

- **Logs**: 20 points each
- **Coal**: 20 points each (rare spawn)
- **Targets**: Required for winning (4 needed)
- **Growth Units**: Track character development (100 needed to win)

## 🔧 Technical Details

- Built on a custom GameEngine framework
- 60 FPS target with adjustable game speed
- Collision detection system
- Entity lifecycle management with garbage collection
- Real-time UI updates showing HP, ammo, score, and growth

## 🎨 Theme & Story

FireBound embraces a fantasy dungeon theme where players control a sentient flame navigating through a hostile underground environment. The character's ability to grow and evolve adds a unique progression element to the traditional scrolling shooter format.

## 📝 Development Notes

This game was developed as an educational project focusing on:
- Object-oriented design patterns
- Interface implementation
- Inheritance hierarchies
- Game loop architecture
- Event-driven programming

The game engine framework was provided, with the game logic and entities implemented as a learning exercise in Java game development.
