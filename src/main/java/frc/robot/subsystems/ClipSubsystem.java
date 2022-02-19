package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class ClipSubsystem extends SubsystemBase {

    private DoubleSolenoid leftPiston;
    private DoubleSolenoid rightPiston;
    private Compressor compressor;
    
    /**
    *
    */
    public ClipSubsystem() {

        leftPiston = RobotMap.leftPiston;
        addChild("leftPiston", leftPiston);
        rightPiston = RobotMap.rightPiston;
        addChild("rightPiston", rightPiston); 
        compressor = RobotMap.compressor;
        addChild("compressor", compressor); 

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    
    public void pistonsForward() {
        leftPiston.set(Value.kForward);
        rightPiston.set(Value.kForward);
    }

    public void pistonsReverse() {
        leftPiston.set(Value.kReverse);
        rightPiston.set(Value.kReverse);
    }

    public void pistonsOff() {
        leftPiston.set(Value.kOff);
        rightPiston.set(Value.kOff);
    }

}

