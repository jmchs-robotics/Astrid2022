package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.Hook;
import frc.robot.subsystems.HookSubsystem;

public class MidClimb extends CommandBase {

    private HookSubsystem m_subsystem;

    /**
     * @param subsystem
     */

    public MidClimb(HookSubsystem subsystem) {

        m_subsystem = subsystem;
        addRequirements(m_subsystem);    

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.setBoth(0.4);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopMotors();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_subsystem.getRightEncoderValue() > 19000;
    }

    @Override
    public boolean runsWhenDisabled() {

        return false;

    }
}
