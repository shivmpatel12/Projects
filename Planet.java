public class Planet {
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;
  public Planet(double xP, double yP, double xV,
        double yV, double m, String img) {
          xxPos = xP;
          yyPos = yP;
          xxVel = xV;
          yyVel = yV;
          mass = m;
          imgFileName = img;
        }
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }
  public double calcDistance(Planet p) {
    return Math.sqrt(((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos)) + ((p.yyPos - this.yyPos) * (p.yyPos - this.yyPos)));
  }
  public double calcForceExertedBy(Planet p) {
    return (6.67e-11 * p.mass * this.mass) / (this.calcDistance(p) * this.calcDistance(p));
  }
  public double calcForceExertedByX(Planet p) {

      return (this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / this.calcDistance(p);

  }
  public double calcForceExertedByY(Planet p) {

      return (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / this.calcDistance(p);

  }
  public double calcNetForceExertedByX(Planet[] planets) {
    double total = 0.0;
    for(Planet planet : planets) {
      if (planet.equals(this)) {
        total += 0;
      }
      else {
        total += this.calcForceExertedByX(planet);
      }
    }
    return total;
  }
  public double calcNetForceExertedByY(Planet[] planets) {
    double total = 0.0;
    for(Planet planet : planets) {
      if (planet.equals(this)) {
        total += 0;
      }
      else {
        total += this.calcForceExertedByY(planet);
      }
    }
    return total;
  }
  public void update(double dt, double fX, double fY) {
    double accX = fX / this.mass;
    double accY = fY / this.mass;
    this.xxVel += (dt * accX);
    this.yyVel += (dt * accY);
    this.xxPos += (dt * this.xxVel);
    this.yyPos += (dt * this.yyVel);
  }
  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
  }
}
