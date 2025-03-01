// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.math.controller.PIDController;

@SuppressWarnings("unused")
public class MoveElevatorToPosition extends Command {
  private final Elevator elevator;
  private final double targetPosition;

  public MoveElevatorToPosition(Elevator elevator, double targetPosition) {
    this.elevator = elevator;
    this.targetPosition = targetPosition;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    System.out.println("Moving elevator to position: " + targetPosition);
  }

  @Override
  public void execute() {
    double speed = elevator.pidController.calculate(elevator.getPosition(), targetPosition);
    speed = Math.max(-ElevatorConstants.KElevatorSpeed, Math.min(ElevatorConstants.KElevatorSpeed, speed)); // Clamp speed between -1 and 1
    elevator.moveElevator(speed);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(elevator.getPosition() - targetPosition) <= ElevatorConstants.kPIDTolerance;
  }

  @Override
  public void end(boolean interrupted) {
    elevator.stopElevator();
    System.out.println("Elevator reached target position: " + targetPosition);
  }
}

