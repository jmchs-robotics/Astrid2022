package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Constants.Hook;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 *
 */
public class HookSubsystem extends SubsystemBase {

private WPI_TalonFX leftHookMotor;
private WPI_TalonFX rightHookMotor;
private MotorControllerGroup bothHooks;
private DifferentialDrive hookDrive;
private double deadband = Hook.deadband;

    public HookSubsystem() {
        leftHookMotor = RobotMap.leftHookMotor;
        addChild("leftHookMotor",leftHookMotor);

        rightHookMotor = RobotMap.rightHookMotor;
        addChild("rightHookMotor",rightHookMotor);

        bothHooks = RobotMap.bothHooks;
        addChild("Motor Controller Group 1",bothHooks);

        hookDrive = RobotMap.hookDrive;
        addChild("Differential Hook Drive", hookDrive);

    }

    @Override
    public void periodic() {

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

    public void hookArcade(double both, double correction) {
        hookDrive.arcadeDrive(both, correction);
    }

    public void hookTank(double leftSpeed, double rightSpeed) {
        hookDrive.tankDrive(leftSpeed, rightSpeed);
    }


    /**
     * @desc check hook limits
     * @param speed
     * @return true if within limits; false if limits are broken
     */
    public boolean checkUpperLimits() {
        return getRightEncoderValue() > Hook.upperRightPos; //&& getLeftEncoderValue() < Hook.upperLeftPos;
    }

    public boolean checkLowerLimits() {
        return getRightEncoderValue() < Hook.lowerRightPos; //&& getLeftEncoderValue() < Hook.lowerLeftPos;
    }

    public void hookLimiter(double control){
        if(control > deadband && checkUpperLimits()) {
            setBoth(control);
          }
          else if(control < -deadband && checkLowerLimits()) {
            setBoth(control);
          }
          else {
            stopMotors();
          }
    }

    public void hookArcadeLimiter(double control, double offset){
        if((control > deadband) && checkUpperLimits()) {
            hookArcade(control, offset);
          }
        else if((control < -deadband) && checkLowerLimits()) {
            hookArcade(control, offset);
          }
        else if((offset != 0) && checkUpperLimits() && checkLowerLimits()) {
            hookArcade(control, offset);
        } else {
            stopMotors();
        }     
    }

    public void hookTankLimiter(double left, double right){
        if((left > deadband  || right > deadband) && checkUpperLimits()) {
            hookTank(left, right);
          }
          else if((left < -deadband || right < -deadband) && checkLowerLimits()) {
            hookTank(left, right);

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

