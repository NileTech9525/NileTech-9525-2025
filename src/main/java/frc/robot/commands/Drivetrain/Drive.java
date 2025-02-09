// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Drive extends Command {
  private Drivetrain drivetrain;
  private CommandXboxController driverController;

  public Drive(Drivetrain drivetrain, CommandXboxController driverController) {
    
    this.drivetrain = drivetrain;
    this.driverController = driverController;

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.stop(0, 0);
  }

  @Override
  public void execute() { 
    drivetrain.arcadeDrive(driverController.getLeftY(), driverController.getRightX());
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stop(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
