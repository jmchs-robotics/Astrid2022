package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Constants.Hook;
import frc.robot.commands.RetractHook;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 *
 */
public class HookSubsystem extends SubsystemBase {

private WPI_TalonFX leftHookMotor;
private WPI_TalonFX rightHookMotor;
private MotorControllerGroup bothHooks;
private DifferentialDrive hookDrive;
private double hookDifference; 
private double deadband = Hook.deadband;

    public HookSubsystem() {
        leftHookMotor = RobotMap.leftHookMotor;
        addChild("leftHookMotor",leftHookMotor);

        rightHookMotor = RobotMap.rightHookMotor;
        addChild("rightHookMotor",rightHookMotor);

        bothHooks = RobotMap.bothHooks;
        addChild("Motor Controller Group 1",bothHooks);

        hookDrive = RobotMap.hookDrive;
        addChild("Hook Drive", hookDrive);
    }

    @Override
    public void periodic() {
        //positive is left higher and negative is right higher
        hookDifference = getRightEncoderValue() - getLeftEncoderValue();
        SmartDashboard.putNumber("Hook Difference: ", hookDifference);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    /**
     * @desc sets the speed of the hook motors
     * @param speed
     */

    public void setBoth(double speed) {
        bothHooks.set(speed);  
    }

    public void setLeft(double speed) {
        leftHookMotor.set(speed);
    }

    public void setRight(double speed) {
        rightHookMotor.set(speed);
    }

    /**
     * @desc check hook limits
     * @param speed
     * @return true if within limits; false if limits are broken
     */
    public boolean checkUpperRightLimit() {
        return getRightEncoderValue() > Hook.maxPos;
    }

    public boolean checkUpperLeftLimit() {
        return getLeftEncoderValue() > Hook.maxPos;
    }

    public boolean checkUpperLimits() {
        return checkUpperLeftLimit() && checkUpperRightLimit();
    }

    public boolean checkLowerLeftLimit() {
        return getLeftEncoderValue() < Hook.minPos;
    }

    public boolean checkLowerRightLimit() {
        return getRightEncoderValue() < Hook.minPos;
    }

    public boolean checkLowerLimits() {
        return checkLowerLeftLimit() && checkLowerRightLimit();
    }

    //Going up with left/right correction
    public void upHookCorrection() {

        if (hookDifference > 800) {
            setRight(-0.4);
        }
        else if (hookDifference < -800) {
            setLeft(-0.4);
        }
        else {
            setBoth(-0.4);
        }
    }

    //Going down with left/right correction
    public void downHookCorrection() {
        if (hookDifference > 800) {
            setLeft(0.4);
        }
        else if (hookDifference < -800) {
            setRight(0.4);
        }
        else {
            setBoth(0.4);
        }
    }
    
    //Hook Control
    public void hookLimiter(double control){
        if((control < -deadband) && checkUpperLimits()) { //both up
            upHookCorrection();
        }
        else if((control > deadband) && checkLowerLimits()) { //both down
            downHookCorrection();
        }
        else {
            stopMotors();
        }
    }
    

    public void hookArcadeLimiter(double control, double offset){

        if((control < -deadband) && checkUpperLimits()) { //both up
            setBoth(-0.4);
        }
        
        else if(control > deadband) { //both down
            setBoth(0.4);
        }
        
        else if (offset < -deadband && checkLowerRightLimit() && checkUpperLeftLimit()) {
            setLeft(-0.1);
            setRight(0.1);
        }
        
        else if (offset > deadband && checkUpperRightLimit() && checkLowerLeftLimit()) {
            setLeft(0.1);
            setRight(-0.1);
        }    
        
        else {
            stopMotors();
        }     
    }   

    public void hookTankLimiter(double left, double right){
        if((left > deadband) && checkUpperLeftLimit()) {
            setLeft(0.4);
        }
        else if((right > deadband) && checkUpperRightLimit()) {
            setRight(0.4);
        }
        else if((left < -deadband) && checkLowerLeftLimit()) {
            setLeft(-0.4);
        }
        else if((right < -deadband) && checkLowerRightLimit()) {
            setRight(-0.4);
        }
        else {
            stopMotors();
        }
    }

    /**
     * @desc stops the hook motors
     */

    public void stopMotors() {
        bothHooks.stopMotor();
    }

    public void stopLeft() {
        leftHookMotor.stopMotor();
    }

    public void stopRight() {
        rightHookMotor.stopMotor();
    }

    public void resetEncoderValue() {
        leftHookMotor.setSelectedSensorPosition(0);
		rightHookMotor.setSelectedSensorPosition(0);
	}

    public double getLeftEncoderValue() {
        return leftHookMotor.getSelectedSensorPosition();
    }

    public double getRightEncoderValue() {
        return rightHookMotor.getSelectedSensorPosition();

    }

}

