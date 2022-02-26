package frc.robot.commands;

import org.w3c.dom.UserDataHandler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {

	private Drivetrain m_subsystem;

	double endVal;
	double vBus;
	double initialHeading;
	boolean useGyro;
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
    	useGyro = false;
		useEncoders = false;
    }

	/**
	 * @desc Drive command with gyro stabilization
	 * @param subsystem
	 * @param percentVBus
	 * @param gyro TRUE to use gyroscope
	 */
	public DriveStraight(Drivetrain subsystem, double percentVBus, boolean gyro) {

		m_subsystem = subsystem;
    	addRequirements(m_subsystem);
    	
		vBus = percentVBus;
    	useGyro = gyro;
		useEncoders = false;
    }
    
    /**
     * @desc Command that drives straight with the help of encoders
     * @param inches Needs to be negative for backwards movement, positive otherwise.
     * @param percentVBus Requires same sign as inches.
     * @param useEncoders TRUE to use encoders.
     */
    public DriveStraight(Drivetrain subsystem, double inches, double percentVBus, boolean usePD) {
    	
		m_subsystem = subsystem;
		addRequirements(m_subsystem);
    	
    	endVal = inches * m_subsystem.kEncoderTicksPerInch;
    	distThisLeg = endVal;
    	vBus = percentVBus;
    	this.usePD = usePD;

    	//direction is positive for forwards and negative for backwards.
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	endVal += m_subsystem.getRightEncoderPos(0);
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = m_subsystem.getGyroHeading();
    
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double proportion = m_subsystem.kPGyroConstant * (m_subsystem.getGyroHeading() - initialHeading);
    	double left_coefficient = 1;
		double right_coefficient = 0.985;
    	
    	if(usePD) {    		
			
			
    	}
    	
    	m_subsystem.tankDrive(left_coefficient * vBus, right_coefficient * vBus);
		
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(usePD) {
    		// have we gone far enough?
    		if(Math.signum(vBus) < 0) {
    			return m_subsystem.getRightEncoderPos(0) <= endVal;
    		} else {
    			return m_subsystem.getRightEncoderPos(0) >= endVal;
    		}
    		
    	}
		
		return false;
    }
    
    public boolean exposedIsFinished() {
    	return isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_subsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
