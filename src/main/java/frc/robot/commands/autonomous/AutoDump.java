package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PullDumpArm;
import frc.robot.commands.PushDumpArm;
import frc.robot.subsystems.LArmSubsystem;

public class AutoDump extends CommandBase{

    LArmSubsystem m_LArm;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public AutoDump(LArmSubsystem larm) {
        m_LArm = larm;
        addRequirements(m_LArm);


        new SequentialCommandGroup(
            new PushDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(0.25),
            new PullDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(0.25)
        );
    }    

}
