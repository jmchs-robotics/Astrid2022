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

    public HookSubsystem() {
        leftHookMotor = RobotMap.leftHookMotor;
        leftHookMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
        addChild("leftHookMotor",leftHookMotor);

        rightHookMotor = RobotMap.rightHookMotor;
        rightHookMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
        addChild("rightHookMotor",rightHookMotor);

        bothHooks = RobotMap.bothHooks;
        addChild("Motor Controller Group 1",bothHooks);

        hookDrive = RobotMap.hookDrive;
        addChild("Motor Controller Group 1",bothHooks);

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

    public void setSpeed(double speed){
        bothHooks.set(speed);  
    }

    public void hookCorrectionArcade(double speed, double rotation){
        hookDrive.arcadeDrive(speed, rotation);
    }

    public void hookCorrectionTank(double leftSpeed, double rightSpeed){
        hookDrive.tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * @desc stops the hook motors
     */

    public void stopMotors() {
        bothHooks.stopMotor();
    }

    public void resetEncoderValue() {
        leftHookMotor.setSelectedSensorPosition(0);
		rightHookMotor.setSelectedSensorPosition(0);
	}

    public double getEncoderValue(boolean left) {
		if (left) {
            return leftHookMotor.getSelectedSensorPosition();
        } else {
            return rightHookMotor.getSelectedSensorPosition();
        }

    }

}

