 package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class k1_GroundPosition extends SequentialCommandGroup {
  public k1_GroundPosition(Elevator elevator) {
    addCommands(
        new MoveElevatorToPosition(elevator, -2) // Ground level: 0 meters
    );
  }
} 