package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClipSubsystem;

/**
 *
 */
public class PullClipArm extends CommandBase {

    private ClipSubsystem m_subsystem;

    /**
     * @param subsystem
     */

    public PullClipArm(ClipSubsystem subsystem) {

        this.m_subsystem = subsystem;
        addRequirements(subsystem); 

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.pistonsReverse();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.pistonsOff();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {

        return false;

    }
}
