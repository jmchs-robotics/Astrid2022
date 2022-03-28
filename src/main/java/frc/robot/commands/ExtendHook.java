package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.Hook;
import frc.robot.subsystems.HookSubsystem;

public class ExtendHook extends CommandBase {

    private HookSubsystem m_subsystem;
    private boolean fullExtend;

    /**
     * @param subsystem
     */

    public ExtendHook(HookSubsystem subsystem, boolean fullExtend) {

        m_subsystem = subsystem;
        this.fullExtend = fullExtend;
        addRequirements(m_subsystem);    

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_subsystem.getLeftEncoderValue() < Hook.upperLeftPos) {
            m_subsystem.setLeft(-0.2);
        } else {
            m_subsystem.stopLeft();
        }

        if (m_subsystem.getRightEncoderValue() > Hook.upperRightPos) {
            m_subsystem.setRight(-0.2);
        } else {
            m_subsystem.stopRight();
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
        if (fullExtend) {
            return m_subsystem.getRightEncoderValue() < Hook.upperRightPos; //&& m_subsystem.getLeftEncoderValue() > Hook.upperLeftPos && );
        }
        else {
            return !(m_subsystem.checkUpperLimits());
        }
        
    }

    @Override
    public boolean runsWhenDisabled() {

        return false;

    }
}
