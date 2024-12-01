package robot.drive;

import java.util.List;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;

public class Drive extends SubsystemBase {
    private final CANSparkMax LeftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax LeftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax RightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax RightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);

    // Constructor
    public Drive() {
        for (CANSparkMax spark : List.of(LeftLeader, LeftFollower, RightLeader, RightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake);
        }

      for (CANSparkMax spark : List.of(LeftLeader, LeftFollower, RightLeader, RightFollower)) {
	    spark.restoreFactoryDefaults();
	    spark.setIdleMode(IdleMode.kBrake);
 
    RightFollower.follow(RightLeader);
    LeftFollower.follow(LeftLeader);
    LeftLeader.setInverted(true);
    }  
}

    private void drive(double leftSpeed, double rightSpeed) {
        LeftLeader.set(leftSpeed);
        RightLeader.set(rightSpeed);
  }

    public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight) {
    return run(() -> drive(vLeft.getAsDouble(), vRight.getAsDouble()));
  }

}

