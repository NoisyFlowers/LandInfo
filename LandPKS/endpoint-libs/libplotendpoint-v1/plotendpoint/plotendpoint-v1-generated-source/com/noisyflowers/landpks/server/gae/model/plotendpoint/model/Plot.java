/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-03-26 20:30:19 UTC)
 * on 2015-06-29 at 19:31:42 UTC 
 * Modify at your own risk.
 */

package com.noisyflowers.landpks.server.gae.model.plotendpoint.model;

/**
 * Model definition for Plot.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the plotendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Plot extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double averageAnnualPrecipitation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double awcSoilProfile;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer bedrockDepth;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String city;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon3;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon4;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon5;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon6;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer colorForSoilHorizon7;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double cropErosion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double cropProductivity;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean flooding;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double gdalAridityIndex;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double gdalElevation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String gdalFaoLgp;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double grassErosion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double grassProductivity;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean grazed;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> grazing;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String landCover;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String landscapeEastPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String landscapeNorthPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String landscapeSouthPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String landscapeWestPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double latitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime modifiedDate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> monthlyAvgTemperature;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> monthlyMaxTemperature;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> monthlyMinTemperature;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.Double> monthlyPrecipitation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String organization;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String recommendation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String recorderName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon3;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon4;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon5;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon6;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rockFragmentForSoilHorizon7;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String slope;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String slopeShape;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String soilPitPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String soilSamplesPhotoURL;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer stoppedDiggingDepth;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean surfaceCracking;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean surfaceSalt;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean testPlot;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon3;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon4;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon5;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon6;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String textureForSoilHorizon7;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getAverageAnnualPrecipitation() {
    return averageAnnualPrecipitation;
  }

  /**
   * @param averageAnnualPrecipitation averageAnnualPrecipitation or {@code null} for none
   */
  public Plot setAverageAnnualPrecipitation(java.lang.Double averageAnnualPrecipitation) {
    this.averageAnnualPrecipitation = averageAnnualPrecipitation;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getAwcSoilProfile() {
    return awcSoilProfile;
  }

  /**
   * @param awcSoilProfile awcSoilProfile or {@code null} for none
   */
  public Plot setAwcSoilProfile(java.lang.Double awcSoilProfile) {
    this.awcSoilProfile = awcSoilProfile;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getBedrockDepth() {
    return bedrockDepth;
  }

  /**
   * @param bedrockDepth bedrockDepth or {@code null} for none
   */
  public Plot setBedrockDepth(java.lang.Integer bedrockDepth) {
    this.bedrockDepth = bedrockDepth;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCity() {
    return city;
  }

  /**
   * @param city city or {@code null} for none
   */
  public Plot setCity(java.lang.String city) {
    this.city = city;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon1() {
    return colorForSoilHorizon1;
  }

  /**
   * @param colorForSoilHorizon1 colorForSoilHorizon1 or {@code null} for none
   */
  public Plot setColorForSoilHorizon1(java.lang.Integer colorForSoilHorizon1) {
    this.colorForSoilHorizon1 = colorForSoilHorizon1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon2() {
    return colorForSoilHorizon2;
  }

  /**
   * @param colorForSoilHorizon2 colorForSoilHorizon2 or {@code null} for none
   */
  public Plot setColorForSoilHorizon2(java.lang.Integer colorForSoilHorizon2) {
    this.colorForSoilHorizon2 = colorForSoilHorizon2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon3() {
    return colorForSoilHorizon3;
  }

  /**
   * @param colorForSoilHorizon3 colorForSoilHorizon3 or {@code null} for none
   */
  public Plot setColorForSoilHorizon3(java.lang.Integer colorForSoilHorizon3) {
    this.colorForSoilHorizon3 = colorForSoilHorizon3;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon4() {
    return colorForSoilHorizon4;
  }

  /**
   * @param colorForSoilHorizon4 colorForSoilHorizon4 or {@code null} for none
   */
  public Plot setColorForSoilHorizon4(java.lang.Integer colorForSoilHorizon4) {
    this.colorForSoilHorizon4 = colorForSoilHorizon4;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon5() {
    return colorForSoilHorizon5;
  }

  /**
   * @param colorForSoilHorizon5 colorForSoilHorizon5 or {@code null} for none
   */
  public Plot setColorForSoilHorizon5(java.lang.Integer colorForSoilHorizon5) {
    this.colorForSoilHorizon5 = colorForSoilHorizon5;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon6() {
    return colorForSoilHorizon6;
  }

  /**
   * @param colorForSoilHorizon6 colorForSoilHorizon6 or {@code null} for none
   */
  public Plot setColorForSoilHorizon6(java.lang.Integer colorForSoilHorizon6) {
    this.colorForSoilHorizon6 = colorForSoilHorizon6;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getColorForSoilHorizon7() {
    return colorForSoilHorizon7;
  }

  /**
   * @param colorForSoilHorizon7 colorForSoilHorizon7 or {@code null} for none
   */
  public Plot setColorForSoilHorizon7(java.lang.Integer colorForSoilHorizon7) {
    this.colorForSoilHorizon7 = colorForSoilHorizon7;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getCropErosion() {
    return cropErosion;
  }

  /**
   * @param cropErosion cropErosion or {@code null} for none
   */
  public Plot setCropErosion(java.lang.Double cropErosion) {
    this.cropErosion = cropErosion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getCropProductivity() {
    return cropProductivity;
  }

  /**
   * @param cropProductivity cropProductivity or {@code null} for none
   */
  public Plot setCropProductivity(java.lang.Double cropProductivity) {
    this.cropProductivity = cropProductivity;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getFlooding() {
    return flooding;
  }

  /**
   * @param flooding flooding or {@code null} for none
   */
  public Plot setFlooding(java.lang.Boolean flooding) {
    this.flooding = flooding;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getGdalAridityIndex() {
    return gdalAridityIndex;
  }

  /**
   * @param gdalAridityIndex gdalAridityIndex or {@code null} for none
   */
  public Plot setGdalAridityIndex(java.lang.Double gdalAridityIndex) {
    this.gdalAridityIndex = gdalAridityIndex;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getGdalElevation() {
    return gdalElevation;
  }

  /**
   * @param gdalElevation gdalElevation or {@code null} for none
   */
  public Plot setGdalElevation(java.lang.Double gdalElevation) {
    this.gdalElevation = gdalElevation;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGdalFaoLgp() {
    return gdalFaoLgp;
  }

  /**
   * @param gdalFaoLgp gdalFaoLgp or {@code null} for none
   */
  public Plot setGdalFaoLgp(java.lang.String gdalFaoLgp) {
    this.gdalFaoLgp = gdalFaoLgp;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getGrassErosion() {
    return grassErosion;
  }

  /**
   * @param grassErosion grassErosion or {@code null} for none
   */
  public Plot setGrassErosion(java.lang.Double grassErosion) {
    this.grassErosion = grassErosion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getGrassProductivity() {
    return grassProductivity;
  }

  /**
   * @param grassProductivity grassProductivity or {@code null} for none
   */
  public Plot setGrassProductivity(java.lang.Double grassProductivity) {
    this.grassProductivity = grassProductivity;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getGrazed() {
    return grazed;
  }

  /**
   * @param grazed grazed or {@code null} for none
   */
  public Plot setGrazed(java.lang.Boolean grazed) {
    this.grazed = grazed;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getGrazing() {
    return grazing;
  }

  /**
   * @param grazing grazing or {@code null} for none
   */
  public Plot setGrazing(java.util.List<java.lang.String> grazing) {
    this.grazing = grazing;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Plot setId(java.lang.String id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLandCover() {
    return landCover;
  }

  /**
   * @param landCover landCover or {@code null} for none
   */
  public Plot setLandCover(java.lang.String landCover) {
    this.landCover = landCover;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLandscapeEastPhotoURL() {
    return landscapeEastPhotoURL;
  }

  /**
   * @param landscapeEastPhotoURL landscapeEastPhotoURL or {@code null} for none
   */
  public Plot setLandscapeEastPhotoURL(java.lang.String landscapeEastPhotoURL) {
    this.landscapeEastPhotoURL = landscapeEastPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLandscapeNorthPhotoURL() {
    return landscapeNorthPhotoURL;
  }

  /**
   * @param landscapeNorthPhotoURL landscapeNorthPhotoURL or {@code null} for none
   */
  public Plot setLandscapeNorthPhotoURL(java.lang.String landscapeNorthPhotoURL) {
    this.landscapeNorthPhotoURL = landscapeNorthPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLandscapeSouthPhotoURL() {
    return landscapeSouthPhotoURL;
  }

  /**
   * @param landscapeSouthPhotoURL landscapeSouthPhotoURL or {@code null} for none
   */
  public Plot setLandscapeSouthPhotoURL(java.lang.String landscapeSouthPhotoURL) {
    this.landscapeSouthPhotoURL = landscapeSouthPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLandscapeWestPhotoURL() {
    return landscapeWestPhotoURL;
  }

  /**
   * @param landscapeWestPhotoURL landscapeWestPhotoURL or {@code null} for none
   */
  public Plot setLandscapeWestPhotoURL(java.lang.String landscapeWestPhotoURL) {
    this.landscapeWestPhotoURL = landscapeWestPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude latitude or {@code null} for none
   */
  public Plot setLatitude(java.lang.Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude longitude or {@code null} for none
   */
  public Plot setLongitude(java.lang.Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getModifiedDate() {
    return modifiedDate;
  }

  /**
   * @param modifiedDate modifiedDate or {@code null} for none
   */
  public Plot setModifiedDate(com.google.api.client.util.DateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getMonthlyAvgTemperature() {
    return monthlyAvgTemperature;
  }

  /**
   * @param monthlyAvgTemperature monthlyAvgTemperature or {@code null} for none
   */
  public Plot setMonthlyAvgTemperature(java.util.List<java.lang.Double> monthlyAvgTemperature) {
    this.monthlyAvgTemperature = monthlyAvgTemperature;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getMonthlyMaxTemperature() {
    return monthlyMaxTemperature;
  }

  /**
   * @param monthlyMaxTemperature monthlyMaxTemperature or {@code null} for none
   */
  public Plot setMonthlyMaxTemperature(java.util.List<java.lang.Double> monthlyMaxTemperature) {
    this.monthlyMaxTemperature = monthlyMaxTemperature;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getMonthlyMinTemperature() {
    return monthlyMinTemperature;
  }

  /**
   * @param monthlyMinTemperature monthlyMinTemperature or {@code null} for none
   */
  public Plot setMonthlyMinTemperature(java.util.List<java.lang.Double> monthlyMinTemperature) {
    this.monthlyMinTemperature = monthlyMinTemperature;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.Double> getMonthlyPrecipitation() {
    return monthlyPrecipitation;
  }

  /**
   * @param monthlyPrecipitation monthlyPrecipitation or {@code null} for none
   */
  public Plot setMonthlyPrecipitation(java.util.List<java.lang.Double> monthlyPrecipitation) {
    this.monthlyPrecipitation = monthlyPrecipitation;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public Plot setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getOrganization() {
    return organization;
  }

  /**
   * @param organization organization or {@code null} for none
   */
  public Plot setOrganization(java.lang.String organization) {
    this.organization = organization;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRecommendation() {
    return recommendation;
  }

  /**
   * @param recommendation recommendation or {@code null} for none
   */
  public Plot setRecommendation(java.lang.String recommendation) {
    this.recommendation = recommendation;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRecorderName() {
    return recorderName;
  }

  /**
   * @param recorderName recorderName or {@code null} for none
   */
  public Plot setRecorderName(java.lang.String recorderName) {
    this.recorderName = recorderName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon1() {
    return rockFragmentForSoilHorizon1;
  }

  /**
   * @param rockFragmentForSoilHorizon1 rockFragmentForSoilHorizon1 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon1(java.lang.String rockFragmentForSoilHorizon1) {
    this.rockFragmentForSoilHorizon1 = rockFragmentForSoilHorizon1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon2() {
    return rockFragmentForSoilHorizon2;
  }

  /**
   * @param rockFragmentForSoilHorizon2 rockFragmentForSoilHorizon2 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon2(java.lang.String rockFragmentForSoilHorizon2) {
    this.rockFragmentForSoilHorizon2 = rockFragmentForSoilHorizon2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon3() {
    return rockFragmentForSoilHorizon3;
  }

  /**
   * @param rockFragmentForSoilHorizon3 rockFragmentForSoilHorizon3 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon3(java.lang.String rockFragmentForSoilHorizon3) {
    this.rockFragmentForSoilHorizon3 = rockFragmentForSoilHorizon3;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon4() {
    return rockFragmentForSoilHorizon4;
  }

  /**
   * @param rockFragmentForSoilHorizon4 rockFragmentForSoilHorizon4 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon4(java.lang.String rockFragmentForSoilHorizon4) {
    this.rockFragmentForSoilHorizon4 = rockFragmentForSoilHorizon4;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon5() {
    return rockFragmentForSoilHorizon5;
  }

  /**
   * @param rockFragmentForSoilHorizon5 rockFragmentForSoilHorizon5 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon5(java.lang.String rockFragmentForSoilHorizon5) {
    this.rockFragmentForSoilHorizon5 = rockFragmentForSoilHorizon5;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon6() {
    return rockFragmentForSoilHorizon6;
  }

  /**
   * @param rockFragmentForSoilHorizon6 rockFragmentForSoilHorizon6 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon6(java.lang.String rockFragmentForSoilHorizon6) {
    this.rockFragmentForSoilHorizon6 = rockFragmentForSoilHorizon6;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRockFragmentForSoilHorizon7() {
    return rockFragmentForSoilHorizon7;
  }

  /**
   * @param rockFragmentForSoilHorizon7 rockFragmentForSoilHorizon7 or {@code null} for none
   */
  public Plot setRockFragmentForSoilHorizon7(java.lang.String rockFragmentForSoilHorizon7) {
    this.rockFragmentForSoilHorizon7 = rockFragmentForSoilHorizon7;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSlope() {
    return slope;
  }

  /**
   * @param slope slope or {@code null} for none
   */
  public Plot setSlope(java.lang.String slope) {
    this.slope = slope;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSlopeShape() {
    return slopeShape;
  }

  /**
   * @param slopeShape slopeShape or {@code null} for none
   */
  public Plot setSlopeShape(java.lang.String slopeShape) {
    this.slopeShape = slopeShape;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSoilPitPhotoURL() {
    return soilPitPhotoURL;
  }

  /**
   * @param soilPitPhotoURL soilPitPhotoURL or {@code null} for none
   */
  public Plot setSoilPitPhotoURL(java.lang.String soilPitPhotoURL) {
    this.soilPitPhotoURL = soilPitPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSoilSamplesPhotoURL() {
    return soilSamplesPhotoURL;
  }

  /**
   * @param soilSamplesPhotoURL soilSamplesPhotoURL or {@code null} for none
   */
  public Plot setSoilSamplesPhotoURL(java.lang.String soilSamplesPhotoURL) {
    this.soilSamplesPhotoURL = soilSamplesPhotoURL;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getStoppedDiggingDepth() {
    return stoppedDiggingDepth;
  }

  /**
   * @param stoppedDiggingDepth stoppedDiggingDepth or {@code null} for none
   */
  public Plot setStoppedDiggingDepth(java.lang.Integer stoppedDiggingDepth) {
    this.stoppedDiggingDepth = stoppedDiggingDepth;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getSurfaceCracking() {
    return surfaceCracking;
  }

  /**
   * @param surfaceCracking surfaceCracking or {@code null} for none
   */
  public Plot setSurfaceCracking(java.lang.Boolean surfaceCracking) {
    this.surfaceCracking = surfaceCracking;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getSurfaceSalt() {
    return surfaceSalt;
  }

  /**
   * @param surfaceSalt surfaceSalt or {@code null} for none
   */
  public Plot setSurfaceSalt(java.lang.Boolean surfaceSalt) {
    this.surfaceSalt = surfaceSalt;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getTestPlot() {
    return testPlot;
  }

  /**
   * @param testPlot testPlot or {@code null} for none
   */
  public Plot setTestPlot(java.lang.Boolean testPlot) {
    this.testPlot = testPlot;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon1() {
    return textureForSoilHorizon1;
  }

  /**
   * @param textureForSoilHorizon1 textureForSoilHorizon1 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon1(java.lang.String textureForSoilHorizon1) {
    this.textureForSoilHorizon1 = textureForSoilHorizon1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon2() {
    return textureForSoilHorizon2;
  }

  /**
   * @param textureForSoilHorizon2 textureForSoilHorizon2 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon2(java.lang.String textureForSoilHorizon2) {
    this.textureForSoilHorizon2 = textureForSoilHorizon2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon3() {
    return textureForSoilHorizon3;
  }

  /**
   * @param textureForSoilHorizon3 textureForSoilHorizon3 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon3(java.lang.String textureForSoilHorizon3) {
    this.textureForSoilHorizon3 = textureForSoilHorizon3;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon4() {
    return textureForSoilHorizon4;
  }

  /**
   * @param textureForSoilHorizon4 textureForSoilHorizon4 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon4(java.lang.String textureForSoilHorizon4) {
    this.textureForSoilHorizon4 = textureForSoilHorizon4;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon5() {
    return textureForSoilHorizon5;
  }

  /**
   * @param textureForSoilHorizon5 textureForSoilHorizon5 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon5(java.lang.String textureForSoilHorizon5) {
    this.textureForSoilHorizon5 = textureForSoilHorizon5;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon6() {
    return textureForSoilHorizon6;
  }

  /**
   * @param textureForSoilHorizon6 textureForSoilHorizon6 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon6(java.lang.String textureForSoilHorizon6) {
    this.textureForSoilHorizon6 = textureForSoilHorizon6;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTextureForSoilHorizon7() {
    return textureForSoilHorizon7;
  }

  /**
   * @param textureForSoilHorizon7 textureForSoilHorizon7 or {@code null} for none
   */
  public Plot setTextureForSoilHorizon7(java.lang.String textureForSoilHorizon7) {
    this.textureForSoilHorizon7 = textureForSoilHorizon7;
    return this;
  }

  @Override
  public Plot set(String fieldName, Object value) {
    return (Plot) super.set(fieldName, value);
  }

  @Override
  public Plot clone() {
    return (Plot) super.clone();
  }

}