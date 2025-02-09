package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClawConstants;
import frc.robot.subsystems.Claw;

public class AlgaeLaunch extends Command {
    private final Claw claw;
    private final double targetVelocity;

    public AlgaeLaunch(Claw claw) {
        this.claw = claw;
        this.targetVelocity = ClawConstants.kAlgaeTarget; // Set target velocity for AlgaeLaunch
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.setVelocity(targetVelocity);
    }

    @Override
    public void execute() {
        // Monitor velocity on SmartDashboard (optional)
    }

    @Override
    public void end(boolean interrupted) {
        claw.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Run until explicitly canceled
    }
}
