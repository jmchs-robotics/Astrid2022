package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Hook;
import frc.robot.subsystems.HookSubsystem;

/**
 *
 */
public class RetractHook extends CommandBase {

    private HookSubsystem m_subsystem;
    private boolean fullRetract;

    /**
     * @param subsystem
     */

    public RetractHook(HookSubsystem subsystem, boolean fullRetract) {

        m_subsystem = subsystem;
        this.fullRetract = fullRetract;
        addRequirements(m_subsystem); 

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_subsystem.getLeftEncoderValue() > Hook.lowerLeftPos) {
            m_subsystem.setLeft(0.2);
        }
        if (m_subsystem.getRightEncoderValue() < Hook.lowerRightPos) {
            m_subsystem.setRight(0.2);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopMotors();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (fullRetract) {
            return m_subsystem.getRightEncoderValue() > Hook.lowerRightPos; //&& m_subsystem.getLeftEncoderValue() < Hook.lowerLeftPos);
        }
        else {
            return !(m_subsystem.checkLowerLimits());
        }
        
    }

    @Override
    public boolean runsWhenDisabled() {

        return false;

    }
}
