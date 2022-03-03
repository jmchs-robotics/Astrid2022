package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Constants.Hook;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 *
 */
public class HookSubsystem extends SubsystemBase {

private WPI_TalonFX leftHookMotor;
private WPI_TalonFX rightHookMotor;
private MotorControllerGroup bothhooks;

    public HookSubsystem() {
        leftHookMotor = RobotMap.leftHookMotor;
        addChild("leftHookMotor",leftHookMotor);

        rightHookMotor = RobotMap.leftHookMotor;
        addChild("rightHookMotor",rightHookMotor);

        bothhooks = RobotMap.hookMotorGroup;
        addChild("Motor Controller Group 1",bothhooks);

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
        bothhooks.set(speed);  
    }

    /**
     * @desc stops the hook motors
     */

    public void stopMotors() {
        bothhooks.stopMotor();
    }

    public void resetEncoderValue() {
		rightHookMotor.setSelectedSensorPosition(0);
	}

    public double getEncoderValue(int index) {
		return rightHookMotor.getSelectedSensorPosition(index);
	}

}

