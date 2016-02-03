import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._


object Versions {
  val Squants = "0.6.1-CIBO"
  val Scala = "2.11.7"
  val ScalaCross = Seq("2.11.7", "2.10.6")

  val ScalaTest = "3.0.0-M12"
  val ScalaCheck = "1.12.5"
  val Json4s = "3.3.0"
}

object Dependencies {
  val scalaTest = Def.setting(Seq("org.scalatest" %%% "scalatest" % Versions.ScalaTest % "test"))
  val scalaCheck = Def.setting(Seq("org.scalacheck" %%% "scalacheck" % Versions.ScalaCheck % "test"))
  val json4s = Def.setting(Seq("org.json4s" %% "json4s-native" % Versions.Json4s % "test"))
}

object Resolvers {
  val typeSafeRepo = "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  val sonatypeNexusReleases = "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases"
  val sonatypeNexusStaging = "Sonatype Nexus Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
	val CiboRepo = "Cibo libraries" at "http://prod-devops2.ops.cibotechnologies.com:8040/artifactory/libs-release-local"
}

object Project {
  val defaultSettings = Seq(
    organization in ThisBuild := "com.squants",

    name := "Squants",

    version in ThisBuild := Versions.Squants,

    licenses := Seq("Apache 2.0" -> url("http://www.opensource.org/licenses/Apache-2.0")),

    homepage := Some(url("http://www.squants.com/")),

    resolvers ++= Seq(
        Resolvers.typeSafeRepo,
        Resolvers.sonatypeNexusSnapshots,
        Resolvers.sonatypeNexusReleases,
        Resolvers.sonatypeNexusStaging,
        Resolvers.CiboRepo
    )
  )
}

object Compiler {
  val defaultSettings = Seq(
    scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation"),

    scalaVersion in ThisBuild := Versions.Scala,

    crossScalaVersions := Versions.ScalaCross
  )
}
object Publish {
  val defaultSettings = Seq(
		publishTo := { Some("Cibo libraries" at "http://prod-devops2.ops.cibotechnologies.com:8040/artifactory/libs-release-local") }

//	publishTo := {
//      val nexus = "https://oss.sonatype.org/"
//      if (isSnapshot.value)
//        Some("snapshots" at nexus + "content/repositories/snapshots")
//      else
//        Some("releases" at nexus + "service/local/staging/deploy/maven2")
//    },

//    publishMavenStyle := true,
//
//    publishArtifact in Test := false,
//
//    pomIncludeRepository := { _ => false },
//
//    pomExtra := <scm>
//      <url>git@github.com:garyKeorkunian/squants.git</url>
//      <connection>scm:git:git@github.com:garyKeorkunian/squants.git</connection>
//    </scm>
//      <developers>
//        <developer>
//          <id>garyKeorkunian</id>
//          <name>Gary Keorkunian</name>
//          <url>http://www.linkedin.com/in/garykeorkunian</url>
//        </developer>
//      </developers>
  )
}

object Tests {
  val defaultSettings = Seq(
    libraryDependencies ++=
      Dependencies.scalaTest.value ++
      Dependencies.scalaCheck.value ++
      Dependencies.json4s.value
  )
}

object Formatting {
  import com.typesafe.sbt.SbtScalariform._

  lazy val defaultSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := defaultPreferences,
    ScalariformKeys.preferences in Test := defaultPreferences
  )

  val defaultPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences()
      .setPreference(AlignParameters, false)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(CompactControlReadability, true)
      .setPreference(CompactStringConcatenation, false)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(FormatXml, true)
      .setPreference(IndentLocalDefs, false)
      .setPreference(IndentPackageBlocks, true)
      .setPreference(IndentSpaces, 2)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
      .setPreference(PreserveSpaceBeforeArguments, false)
      .setPreference(PreserveDanglingCloseParenthesis, false)
      .setPreference(RewriteArrowSymbols, true)
      .setPreference(SpaceBeforeColon, false)
      .setPreference(SpaceInsideBrackets, false)
      .setPreference(SpacesWithinPatternBinders, true)
  }
}

object Console {
  val defaultSettings = Seq(
  initialCommands in console := """
     import scala.language.postfixOps,
         squants._,
         squants.energy._,
         squants.electro._,
         squants.market._,
         squants.mass._,
         squants.motion._,
         squants.photo._,
         squants.radio._,
         squants.space._,
         squants.storage._,
         squants.thermal._,
         squants.time._,
         squants.DimensionlessConversions._,
         squants.electro.CapacitanceConversions._,
         squants.electro.ConductivityConversions._,
         squants.electro.ElectricalConductanceConversions._,
         squants.electro.ElectricalResistanceConversions._,
         squants.electro.ElectricChargeConversions._,
         squants.electro.ElectricCurrentConversions._,
         squants.electro.ElectricPotentialConversions._,
         squants.electro.InductanceConversions._,
         squants.electro.MagneticFluxConversions._,
         squants.electro.MagneticFluxDensityConversions._,
         squants.electro.ResistivityConversions._,
         squants.energy.EnergyConversions._,
         squants.energy.EnergyDensityConversions._,
         squants.energy.PowerConversions._,
         squants.energy.PowerRampConversions._,
         squants.energy.SpecificEnergyConversions._,
         squants.market.MoneyConversions._,
         squants.mass.AreaDensityConversions._,
         squants.mass.ChemicalAmountConversions._,
         squants.mass.DensityConversions._,
         squants.mass.MassConversions._,
         squants.motion.AccelerationConversions._,
         squants.motion.AngularVelocityConversions._,
         squants.motion.ForceConversions._,
         squants.motion.JerkConversions._,
         squants.motion.MassFlowConversions._,
         squants.motion.MomentumConversions._,
         squants.motion.PressureConversions._,
         squants.motion.VelocityConversions._,
         squants.motion.VolumeFlowConversions._,
         squants.motion.YankConversions._,
         squants.photo.IlluminanceConversions._,
         squants.photo.LuminanceConversions._,
         squants.photo.LuminousEnergyConversions._,
         squants.photo.LuminousExposureConversions._,
         squants.photo.LuminousFluxConversions._,
         squants.photo.LuminousIntensityConversions._,
         squants.radio.IrradianceConversions._,
         squants.radio.RadianceConversions._,
         squants.radio.RadiantIntensityConversions._,
         squants.radio.SpectralIntensityConversions._,
         squants.radio.SpectralPowerConversions._,
         squants.space.AngleConversions._,
         squants.space.AreaConversions._,
         squants.space.LengthConversions._,
         squants.space.SolidAngleConversions._,
         squants.space.VolumeConversions._,
         squants.storage.StorageConversions._,
         squants.thermal.TemperatureConversions._,
         squants.thermal.ThermalCapacityConversions._,
         squants.time.FrequencyConversions._,
         squants.time.TimeConversions._""".stripMargin
  )
}

object SquantsBuild extends Build {

  lazy val defaultSettings =
    Project.defaultSettings ++
    Compiler.defaultSettings ++
    Publish.defaultSettings ++
    Tests.defaultSettings ++
    Formatting.defaultSettings ++
    Console.defaultSettings

  lazy val squants = crossProject
    .crossType(CrossType.Full)
    .in(file("."))
    .settings(defaultSettings: _*)
    .jsSettings(
      excludeFilter in Test := "*Serializer.scala" || "*SerializerSpec.scala"
    )

 	lazy val squantsJVM = squants.jvm
 	lazy val squantsJS = squants.js
}
