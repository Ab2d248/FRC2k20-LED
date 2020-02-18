/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RainbowLED extends SubsystemBase {
  /**
   * Creates a new GradientLED.
   */

  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color temp;
  public static Color[] colors;
  public int counter, redGradient, greenGradient, blueGradient;

  public RainbowLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    counter = 0;
    redGradient = 255;
    greenGradient = 0;
    blueGradient = 0;

    colors = new Color[m_buffer.getLength()];

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      colors[i] = new Color(redGradient, greenGradient, blueGradient);
      m_buffer.setRGB(counter, colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue());
      counter++;
    }

    temp = colors[0];

    led.setData(m_buffer);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    temp = colors[0];

    for(int i = 0; i < m_buffer.getLength()-1; i++)
    {
      colors[i] = colors[i+1];
      m_buffer.setRGB(i, colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue());
    }

    colors[colors.length-1] = temp;

    led.setData(m_buffer);
    Timer.delay(0.05);
  }
}
