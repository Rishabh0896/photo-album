package util;

/**
 * Represents the color as a component of red, green and blue.
 */
public class Color {

  /**
   * Red Component of the color.
   */
  private double redComp;

  /**
   * Green Component of the color.
   */
  private double greenComp;

  /**
   * Blue Component of the color.
   */
  private double blueComp;

  /**
   * Create a color object based on the three primary color components. Added exception handling so
   * association classes can avoid handling there.
   *
   * @param redComp   Red Component of the color.
   * @param greenComp Green Component of the color.
   * @param blueComp  Blue Component of the color.
   */
  public Color(double redComp, double greenComp, double blueComp) {
    if (redComp < 0.0 || greenComp < 0.0 || blueComp < 0.0) {
      throw new IllegalArgumentException("Color components cannot be negative.");
    }
    this.redComp = redComp;
    this.greenComp = greenComp;
    this.blueComp = blueComp;
  }

  /**
   * Getter method that returns red component of the color.
   *
   * @return Red component of the color.
   */
  public double getRedComp() {
    return redComp;
  }

  /**
   * Getter method that returns green component of the color.
   *
   * @return Green component of the color.
   */
  public double getGreenComp() {
    return greenComp;
  }

  /**
   * Getter method that returns blue component of the color.
   *
   * @return Blue component of the color.
   */
  public double getBlueComp() {
    return blueComp;
  }

  /**
   * Update the color components.
   *
   * @param redComp   Updated red component of the color.
   * @param greenComp Updated green component of the color.
   * @param blueComp  Updated blue component of the color.
   */
  public void updateColor(double redComp, double greenComp, double blueComp) {
    if (redComp < 0.0 || greenComp < 0.0 || blueComp < 0.0) {
      throw new IllegalArgumentException("Color components cannot be negative.");
    }
    this.redComp = redComp;
    this.greenComp = greenComp;
    this.blueComp = blueComp;
  }

}
