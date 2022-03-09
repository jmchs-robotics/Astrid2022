package frc.robot.commands;

import org.w3c.dom.UserDataHandler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Drive;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {

	private Drivetrain m_subsystem;

	double endVal;
	double vBus;
	double initialHeading;
	boolean useEncoders;
	double direction;
	double distThisLeg;

	/**
	 * @desc Simple drive straight command with only voltage
	 * @param subsystem
	 * @param percentVBus
	 */
  	public DriveStraight(Drivetrain subsystem, double percentVBus) {

		m_subsystem = subsystem;
    	addRequirements(m_subsystem);
    	
		vBus = percentVBus;
		useEncoders = false;
    }

    /**
     * @desc Command that drives straight with the help of encoders
     * @param inches Needs to be negative for backwards movement, positive otherwise.
     * @param percentVBus Requires same sign as inches.
     * @param useEncoders TRUE to use encoders.
     */
    public DriveStraight(Drivetrain subsystem, double percentVBus, double inches) {
    	
		m_subsystem = subsystem;
		addRequirements(m_subsystem);
    	
    	endVal = inches * Drive.kEncoderTicksPerInch;
		initialHeading = endVal;
    	vBus = percentVBus;
		useEncoders = true;

    	//direction is positive for forwards and negative for backwards.
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	endVal += m_subsystem.getRightEncoderPos(0);
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = m_subsystem.getGyroYaw();

		System.out.print("Start Drive Straight Encoder Value: " + m_subsystem.getRightEncoderPos(0));
    
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double proportion = Drive.kP_gyroDriveStraight * (m_subsystem.getGyroYaw() - initialHeading);
    	double leftVal = 1 * vBus;
		double rightVal = 0.985 * vBus;
		double coefficient = 1;
    	
    	if(useEncoders) {    		
    		coefficient = Math.abs( distThisLeg - m_subsystem.getRightEncoderPos(0)) / 2000;    		
    		coefficient = m_subsystem.thresholdVBus(coefficient);
			leftVal *= coefficient;
			rightVal *= coefficient;
    	}
		
		m_subsystem.tankDrive(leftVal - proportion, rightVal + proportion);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(useEncoders) {
    		// have we gone far enough?
    		if(Math.signum(vBus) < 0) {
    			return m_subsystem.getRightEncoderPos(0) <= endVal;
    		} else {
    			return m_subsystem.getRightEncoderPos(0) >= endVal;
    		}
    	}

		return false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	m_subsystem.tankDrive(0, 0);
		System.out.print("Ending Drive Straight Encoder Value: " + m_subsystem.getRightEncoderPos(0));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
