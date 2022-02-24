package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraightTimed extends CommandBase{

    private Drivetrain m_subsystem;

    public double time;
    public double vBus;

    public DriveStraightTimed(Drivetrain subsystem, double time, double percentVBus) {

        m_subsystem = subsystem;
    	addRequirements(m_subsystem);
    	vBus = -percentVBus;

        this.time = time;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //withTimeout(time);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.tankDrive(vBus, vBus);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stopMotors();
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
