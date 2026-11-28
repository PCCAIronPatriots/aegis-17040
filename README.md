# Aegis 17040

**FIRST Tech Challenge | BioBuzz 2026–2027 Season**

This repository contains the software developed by Aegis 17040 for the 2026–2027 FIRST Tech Challenge season, BioBuzz. Our codebase focuses on modular subsystem design, robot control, vision processing, localization, and autonomous development.

## Overview

The robot software is developed using Kotlin and the FTC SDK, with an object-oriented architecture designed to separate hardware control, subsystem logic, and OpMode execution.

Our software integrates Pedro Pathing for autonomous navigation and the goBILDA Pinpoint odometry system for robot localization. The architecture is designed to make the codebase easier to maintain, test, expand, and integrate as the robot evolves throughout the season.

## Programming

| Role | Details |
|---|---|
| Team | Aegis 17040 |
| Lead Programmer | Brady |
| Season | 2026–2027 BioBuzz |
| Language | Kotlin |
| Platform | FIRST Tech Challenge |
| SDK | FTC SDK |
| Vision Hardware | Limelight 3A |
| Pathing | Pedro Pathing |
| Localization | goBILDA Pinpoint |

## Architecture

Our software is organized around modular subsystems. Each subsystem is responsible for managing a specific portion of the robot's functionality, allowing components to be developed and tested independently.

### Subsystems

| Subsystem | Responsibility |
|---|---|
| Drive | Drivetrain movement and control |
| Launcher | Launcher mechanism operation |
| Intake | Game-piece intake and handling |
| Turret | Turret rotation and targeting |
| Vision | Target detection and vision processing |

Subsystems are coordinated through the OpModes, allowing for centralized robot control without unnecessarily coupling individual components.

## Autonomous Navigation

### Pedro Pathing

The robot utilizes Pedro Pathing for autonomous navigation and trajectory following. Pedro Pathing provides path generation and following capabilities to facilitate accurate and repeatable autonomous movement.

### Localization

Robot localization is handled using the goBILDA Pinpoint odometry computer. The Pinpoint system provides position and heading estimates used for navigation and autonomous movement.

The localization system is integrated with our pathing implementation to provide position feedback throughout autonomous operation.

## Vision System

The robot utilizes a Limelight 3A for vision-based target detection and alignment.

Our vision software is designed to process target observations and provide relevant information to other subsystems for use in robot control.

Development areas include:

- Fiducial identification
- Target validation
- Vision-based tracking
- Measurement filtering
- Turret alignment integration

## OpModes

The repository contains OpModes for driver-controlled operation, subsystem testing, and autonomous development.

OpModes coordinate the robot's subsystems and serve as the entry point for robot operation within the FTC control system.

## Technologies

- Kotlin
- Android Studio
- FIRST Tech Challenge SDK
- Limelight 3A
- Pedro Pathing
- goBILDA Pinpoint

## Repository Structure

```text
TeamCode/
└── src/main/java/org/firstinspires/ftc/teamcode/
    ├── subsystems/
    ├── vision/
    ├── opmodes/
    ├── Robot.kt
    ├── Constants.kt
    └── ...
```

## Credits & Dependencies

This project builds upon several open-source libraries, development tools, and hardware platforms. We would like to acknowledge the developers and organizations behind these resources.

### Software & Libraries

| Project | Description | Link |
|---|---|---|
| FTC SDK | Official FIRST Tech Challenge software development kit. | [GitHub](https://github.com/FIRST-Tech-Challenge/FtcRobotController) |
| Pedro Pathing | Path-following library used for autonomous navigation. | [GitHub](https://github.com/Pedro-Pathing/PedroPathing) |
| FTC Dashboard | Dashboard for real-time telemetry, debugging, and configuration. | [GitHub](https://github.com/acmerobotics/ftc-dashboard) |
| Kotlin | Primary programming language used throughout this project. | [Website](https://kotlinlang.org/) |

### Hardware & Documentation

| Hardware | Description | Link |
|---|---|---|
| goBILDA Pinpoint | Odometry computer used for robot localization. | [Product Page](https://www.gobilda.com/pinpoint-odometry-computer/) |
| Limelight 3A | Vision processing hardware used for target detection and tracking. | [Documentation](https://docs.limelightvision.io/) |
| goBILDA | Robotics hardware, components, and documentation. | [Website](https://www.gobilda.com/) |

### Acknowledgments

We would like to thank the developers, contributors, and organizations who make these tools and resources available to the FIRST Tech Challenge community.

Their work helps make projects like ours possible.

---

**Aegis 17040**  
FIRST Tech Challenge | 2026–2027 BioBuzz Season
