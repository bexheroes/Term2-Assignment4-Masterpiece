
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Assignment4 extends Application{
    
    Stage stage;
    Scene scene;
    Button button;
    Group group;
    Label carSpeed,level;
    Rectangle background;
    ImageView ourCar;
    Label gameOverLabel,scoreLabel,againLabel,highScoreLabel;
    Object ourCarObject;
    Rectangle upKey,downKey,leftKey,rightKey;
    Rectangle riscCircle;
    Rectangle easyBackground,mediumBackground,hardBackground;
    Label riscCircleLabel;
    String source;
    File file;
    BufferedWriter writer;
    Polygon triangle;
    Rectangle spaceRect;
    Label infAboutTurboLabel;
    MediaPlayer speedUpSound;
    MediaPlayer backSoundPlayer;
    MediaPlayer brakeSound;
    MediaPlayer speedUpSoundPlayer;
    MediaPlayer carCrashPlayer;   
    MediaPlayer nitroPlayer;
    MediaPlayer constantSpeedPlayer;
    ImageView closeMusicView;
    ImageView closeAllMusicView;
    Label closeSoundsLabel;
    Label closeAllSoundsLabel;
    ImageView speedMeter;
    Label speedMeterText;
    ImageView speedMeterNeedle;
    Rectangle theExperienceOut,theExperienceIn;
    ImageView mud,oil;
    Polygon previous,next;
    ImageView preview,preview2,preview3;
    Label infoLabel;
    ImageView info;
    MediaPlayer hornPlayer;
    ImageView horn;
    
    // Constants
    public static final int WIDTH=800,HEIGHT=900;
    public static final int PROPORTION_OF_FOREST = 18;
    public static final int PROPORTION_OF_ROAD = 64;
    public static final int CAR_HEIGHT = 114;
    public static final int CAR_WIDTH = 61;
    public static final int CAR_HEIGHT2 = 112;
    public static final int CAR_WIDTH2 = 57;
    public static final int CAR_HEIGHT3 = 108;
    public static final int CAR_WIDTH3 = 60;
    public static final int OUR_CAR_HEIGHT = 120;
    public static final int OUR_CAR_WIDTH = 65;;
    public static final int OUR_CAR2_HEIGHT = 110;
    public static final int OUR_CAR2_WIDTH = 60;
    public static final int OUR_CAR3_HEIGHT = 111;
    public static final int OUR_CAR3_WIDTH = 68;
    public static final double SPACE_BETWEEN_CARS_CONSTANT = CAR_HEIGHT + 144;
    public static double SPACE_BETWEEN_CARS = CAR_HEIGHT + 144; // Exception
    public static int HORIZONTAL_VELOCITY = 7; // Car Exception
    public static final int ROAD_LINE_WIDTH = 10;
    public static final double SPACE_BETWEEN_LINES = 18;
    public static final double ROAD_LINE_HEIGHT = 24;
    public static final int TREE_MIN_RADIUS = 50;
    public static final int TREE_MAX_RADIUS = 62;
    public static final int TREE_MIN_SPACE = 175;
    public static final int TREE_MAX_SPACE = 275;
    public static final int KEY_WIDTH = 42;
    public static final int KEY_HEIGHT = 35;
    public static final double KEY_OPACITY = 0.2;
    public static final double KEY_TEXT_OPACITY = 0.82;
    public static final double PRESS_OPACITY = 0.4;
    public static final int RISK_INTERVAL = 20;
    public static final int RISC_CIRCLE_WIDTH = 120;
    public static final int RISC_CIRCLE_HEIGHT = 36;
    public static final double RISC_CIRCLE_OPACITY = 0.5;
    public static final double DIFFICULTY_RECTANGLE_OPACITY = 0.7;
    public static final double TURBO_OPACITY = 0.7;
    public static final double TRIANGLE_OPACITY = 0.64;
    public static final double SPACERECT_OPACITY = 0.92;
    public static final int OIL_WIDTH = 106;
    public static final int MUD_WIDTH = 100;
    public static final int SPACE_BETWEEN_TREES = 192;
    public static final double TREE_OPACITY = 0.4;
    public static final double CAR_OPACITY = 0.8;
    public static final double ROAD_LINE_OPACITY = 0.88;
    public static final double HORN_OPACITY = 0.5;
    
    // Variables
    public double gameSpeed=0;
    public int score_live=0,level_live=1,experience=0;
    public int endedGame=0;
    public int carPositionX=0,carPositionY=0;
    public int theTreePositionY = 10;
    public int rivalCarPositionY = 0;
    public int HighScore=0;
    public int difficulty = 1;
    public int turbo = 100;
    public int crashEffect = 1;
    public int muteSounds = 0;
    public int muteAllSounds = 0;
    public int carType = 0;
    public double speed_rate = 1;
    public double resistent_rate = 1;
    public double movement_rate = 1;
    public double gameSpeedTop;
    public double hard_rate = 1;
    
    // Sound Files
    public String backSoundSource = "sounds/backSound.mp3";
    public String speedUpSoundSource = "sounds/speedUp.mp3";
    public String brakeSoundSrc = "sounds/brake.mp3";
    public String carCrashSrc = "sounds/carCrash.mp3";
    public String nitroSrc = "sounds/nitro.mp3";
    public String constantSpeedSrc = "sounds/constantSpeed.mp3";
    public String hornSrc = "sounds/horn.mp3";
    
    // Image Files
    public String closeMusicImg = "image/closeSound.png";
    public String closeAllMusicImg = "image/closeAllSound.png";
    public String treeSrc = "image/tree.png";
    public String treeSrc2 = "image/tree2.png";
    public String treeSrc3 = "image/tree3.png";
    public String treeSrc4 = "image/tree4.png";
    public String ourCarSrc = "image/car.png";
    public String ourCarSrc2 = "image/car2.png";
    public String ourCarSrc3 = "image/car3.png";
    public String rivalCarSrc = "image/rivalCar.png";
    public String rivalCarSrc2 = "image/rivalCar2.png";
    public String rivalCarSrc3 = "image/rivalCar3.png";
    public String speedMeterSrc = "image/speedmeter.png";
    public String speedMeterNeedleSrc = "image/needle.png";
    public String theExperienceSrc = "image/xp.png";
    public String mudSrc = "image/mud.png";
    public String oilSrc = "image/oil.png";
    public String carPreviewSrc = "image/carPreview.png";
    public String carPreviewSrc2 = "image/car2Preview.png";
    public String carPreviewSrc3 = "image/car3Preview.png";
    public String infoSrc = "image/info.png";
    public String hornImgSrc = "image/horn.png";
    
    // List And HashMaps
    List<String> input = new ArrayList<String>();
    List<String> roadLines = new ArrayList<String>();
    List<String> leftTree = new ArrayList<String>();
    List<Object> leftTreeSources = new ArrayList<Object>();
    List<String> rightTree = new ArrayList<String>();
    List<Object> rightTreeSources = new ArrayList<Object>();
    List<String> cars = new ArrayList<String>();
    List<Object> carsSources = new ArrayList<Object>();
    List<Object> roadLinesSources = new ArrayList<Object>();
    List<Object> labels = new ArrayList<Object>();
    List<String> holder = new ArrayList<String>();
    HashMap<String, Object> sounds = new HashMap<String, Object>();
    List<String> mudList = new ArrayList<String>();
    List<Object> mudSource = new ArrayList<Object>();
    List<String> oilList = new ArrayList<String>();
    List<Object> oilSource = new ArrayList<Object>();
    
    
    // // // Headers to find with search
    // Create High Score File
    // Main Window
    // Main Layout
    // Design of Forest
    // Design of Road
    // Design of scoarboard and level
    // Design of Car Speed
    // Design of how much experience to get next level
    // Design of our car we controll
    // Add elements to layout
    // Design of the road lines
    // Design of Trees in forest on left side
    // Design of Trees in forest on right side
    // Add rival cars
    // Create Risk Warning Circle 
    // Add text to Risk Circle  
    // Turbo Design
    // Turbo in design
    // Turbo Text   
    // Difficulty Selector
    // Right Bottom Key Viewer
    // Scene
    // Press Action
    // Release Action
    // Change difficulty when click difficulty buttons
    // Output 
    // Game Speed
    // Speed Up Control
    // When press keys blink up Key Buttons
    // Restart everything for new game when press Enter
    // Delete Cars for new order
    // Animation of road lines
    // Delete all
    // Sketch all again
    // Sketch car again to z-index
    // Delete Trees in Forest on both side
    // Sketch Tress in Forest Again on Left Side
    // Sketch Tress in Forest Again on Right Side
    // Delete All Cars
    // Sketch All Cars and Animate
    // Update Scoreboard
    // Level Up Control
    // Crash Control
    // Create background to alert
    // Create text to inform at end of the game
    // Crash Warning
    // Check Difficulty Rectangle Background
    // Update Experience
    // Crash Control Function
    // Crash Risk Function
    
    // Constructor
    public Ass4(){
        score_live = 0;
        level_live = 1;
        
        // Create High Score File
        source = "src/ass4/data.txt";
        file = new File(source);
        if(!(file.exists() && !file.isDirectory())){
            try{
                file.createNewFile();
                try{
                    writer = new BufferedWriter(new FileWriter(file));
                    writer.write("data-"+HighScore);
                    writer.close();
                }catch(Exception e){
                    HighScore = 0;
                }
            }catch(Exception e){
                HighScore = 0;
            }
        }else{
            try{
                Scanner s = new Scanner(file);
                if(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("data-")){
                        String [] splitedData = line.split("-");
                        int getHighScore = Integer.parseInt(splitedData[1]);
                        HighScore = getHighScore;
                    }else{
                        writer = new BufferedWriter(new FileWriter(file));
                        writer.write("data-0");
                        writer.close();
                        HighScore = 0;
                    }
                }
            }catch(Exception e){
                HighScore = 0;
            }
        }
    }
    
    public static void main(String args[]){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
        
        // Design of Our Car
        try{
        Image ourCarImg = new Image(new FileInputStream(ourCarSrc));
        ourCar = new ImageView(ourCarImg);
        }catch(Exception e){
            
        }
        carPositionX = WIDTH/2-CAR_WIDTH/2;
        ourCar.setX(carPositionX);
        carPositionY = HEIGHT*82/100-CAR_HEIGHT/3;
        ourCar.setY(carPositionY);
        ourCar.setFitWidth(OUR_CAR_WIDTH);
        ourCar.setFitHeight(OUR_CAR_HEIGHT);
        ourCarObject = ourCar;
        
        
        // Main Window
        stage = primaryStage;
        stage.setTitle("HUBBM-Racer");
        
        // Main Layout
        group = new Group();
        
        // Design of Forest
        Rectangle leftForest = new Rectangle();
        leftForest.setX(0);
        leftForest.setY(0);
        leftForest.setHeight(HEIGHT);
        leftForest.setWidth(WIDTH*PROPORTION_OF_FOREST/100);
        leftForest.setFill(Color.rgb(29, 142, 47));
        
        Rectangle rightForest = new Rectangle();
        rightForest.setX(WIDTH-WIDTH*PROPORTION_OF_FOREST/100);
        rightForest.setY(0);
        rightForest.setHeight(HEIGHT);
        rightForest.setWidth(WIDTH*PROPORTION_OF_FOREST/100);
        rightForest.setFill(Color.rgb(29, 142, 47));
        
        // Design of Road
        Rectangle road = new Rectangle();
        road.setX(WIDTH*PROPORTION_OF_FOREST/100);
        road.setY(0);
        road.setHeight(HEIGHT);
        road.setWidth(WIDTH*PROPORTION_OF_ROAD/100);
        road.setFill(Color.rgb(140, 164, 162));
        
        // Add Game Sounds
        try{
        Media hornMedia = new Media(new File(hornSrc).toURI().toString());
        hornPlayer = new MediaPlayer(hornMedia);
        hornPlayer.setVolume(0.4);
        Media constantSpeedMedia = new Media(new File(constantSpeedSrc).toURI().toString());
        constantSpeedPlayer = new MediaPlayer(constantSpeedMedia);
        constantSpeedPlayer.setVolume(1);
        Media nitroMedia = new Media(new File(nitroSrc).toURI().toString());
        nitroPlayer = new MediaPlayer(nitroMedia);
        nitroPlayer.setVolume(0.4);
        Media carCrashMedia = new Media(new File(carCrashSrc).toURI().toString());
        carCrashPlayer = new MediaPlayer(carCrashMedia);
        carCrashPlayer.setVolume(0.4);
        Media brake = new Media(new File(brakeSoundSrc).toURI().toString());
        brakeSound = new MediaPlayer(brake);
        brakeSound.setVolume(0.5);
        Media backSound = new Media(new File(backSoundSource).toURI().toString());
        backSoundPlayer = new MediaPlayer(backSound);
        Media speedUpSound = new Media(new File(speedUpSoundSource).toURI().toString());
        speedUpSoundPlayer = new MediaPlayer(speedUpSound);
        speedUpSoundPlayer.setVolume(0.18);
        sounds.put("speedUp", speedUpSoundPlayer);
        sounds.put("backSound",backSoundPlayer);
        constantSpeedPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                constantSpeedPlayer.seek(Duration.ZERO);
            }
        });
        hornPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                hornPlayer.seek(Duration.ZERO);
            }
        });
        backSoundPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                backSoundPlayer.seek(Duration.ZERO);
            }
        });
            backSoundPlayer.setVolume(0.4);
            if(muteSounds==0 && muteAllSounds==0){
                backSoundPlayer.play();
            }
        }catch(Exception e){
            
        }
        // Design of scoarboard and level
        String get_score = "Score: "+String.valueOf(score_live);
        Label score = new Label(get_score);
        score.setFont(Font.font(get_score, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        score.setTranslateX(10);
        score.setTranslateY(5);
        score.setTextFill(Color.WHITE);
        score.toFront();
        
        String get_level = "Level: "+String.valueOf(level_live);
        level = new Label(get_level);
        level.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        level.setTranslateX(10);
        level.setTranslateY(32);
        level.setTextFill(Color.WHITE);
        level.toFront();
        
        // Design of Car Speed
        double carSpeedFromTop = HEIGHT*94.1/100;
        double carSpeedFromLeft = WIDTH * 8.4 / 100;
        String get_speed = String.valueOf((int)gameSpeed*20);
        carSpeed = new Label(get_speed);
        carSpeed.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 14));
        carSpeed.setTranslateX(carSpeedFromLeft);
        carSpeed.setTranslateY(carSpeedFromTop);
        carSpeed.setTextFill(Color.WHITE);
        carSpeed.toFront();
        
        // Design of how much experience to get next level
        
        int beforeExperience;
        if(level_live==1){
            beforeExperience = 0;
        }else{
            beforeExperience = ((level_live-1)-(level_live-1)) + 5;
        }
        double calculated = experience / (level_live*level_live+5-beforeExperience) * 100;
        String changableString;
        if(calculated>=80){
            changableString = "[ % % % % _ ]";
        }else if(calculated>=60){
            changableString = "[ % % % _ _ ]";
        }else if(calculated>=40){
            changableString = "[ % % % _ _ ]";
        }else if(calculated>=20){
            changableString = "[ % _ _ _ _ ]";
        }else{
            changableString = "[ _ _ _ _ _ ]";
        }
        
        
        // Add elements to layout
        group.getChildren().add(road);
        group.getChildren().add(leftForest);
        group.getChildren().add(rightForest);
        group.getChildren().add(score);
        group.getChildren().add(level);
        group.getChildren().add(carSpeed);
        group.getChildren().add(ourCar);
        
        // Design of the road lines
        int roadLeft = (WIDTH*PROPORTION_OF_FOREST/100)+(WIDTH*PROPORTION_OF_ROAD/200)-ROAD_LINE_WIDTH/2;
        int roadTop = 3;
        while(true){
            Rectangle theLine = new Rectangle();
            theLine.setX(roadLeft);
            theLine.setY(roadTop);
            theLine.setWidth(ROAD_LINE_WIDTH);
            theLine.setHeight(ROAD_LINE_HEIGHT);
            theLine.setFill(Color.BLACK);
            theLine.setOpacity(ROAD_LINE_OPACITY);
            theLine.toFront();
            group.getChildren().add(theLine);
            roadLinesSources.add(theLine);
            String coordinates = roadLeft+"-"+roadTop;
            roadLines.add(coordinates);
            roadTop+=ROAD_LINE_HEIGHT + SPACE_BETWEEN_LINES;
            if(roadTop > HEIGHT){
                break;
            }
        }
        
        // Create Risk Warning Circle
            int circleFromLeft = WIDTH * 40 / 100;
            int circleFromTop = 100;
            
            int circleFromLeftText = 5;
            int circleFromTopText = 3;
            
            riscCircle = new Rectangle();
            riscCircle.setWidth(RISC_CIRCLE_WIDTH);
            riscCircle.setFill(Color.TRANSPARENT);
            riscCircle.setHeight(RISC_CIRCLE_HEIGHT);
            riscCircle.setOpacity(RISC_CIRCLE_OPACITY);
            riscCircle.setY(circleFromTop);
            riscCircle.setX(circleFromLeft);
            riscCircle.setVisible(false);
            riscCircle.setStroke(Color.RED);
            riscCircle.setStrokeWidth(2);
            
        // Add text to Risk Circle
            String riscCirlceText = "WARNING !";
            riscCircleLabel = new Label(riscCirlceText);
            riscCircleLabel.setTranslateX(circleFromLeft+circleFromLeftText);
            riscCircleLabel.setTranslateY(circleFromTop+circleFromTopText);
            riscCircleLabel.toFront();
            riscCircleLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 22));
            riscCircleLabel.setTextFill(Color.RED);
            riscCircleLabel.setVisible(false);
            group.getChildren().add(riscCircleLabel);
            
        // Turbo Design
            Rectangle turboOut = new Rectangle();
            int turboOutWidth = WIDTH*PROPORTION_OF_FOREST/100*64/100;
            int turboOutHeight = (int) (HEIGHT*2.668/100);
            int turboFromLeft = WIDTH*PROPORTION_OF_FOREST/100/2-turboOutWidth/2 - 17;
            int turboFromTop = (int) (HEIGHT*12.52/100-turboOutHeight/2);
            turboOut.setWidth(turboOutWidth);
            turboOut.setHeight(turboOutHeight);
            turboOut.setX(turboFromLeft);
            turboOut.setY(turboFromTop);
            turboOut.setFill(Color.TRANSPARENT);
            turboOut.setStroke(Color.WHITE);
            turboOut.setStrokeWidth(1);
            turboOut.setArcWidth(6);
            turboOut.setArcHeight(3);
            turboOut.setOpacity(TURBO_OPACITY);
            group.getChildren().add(turboOut);
            
            // Turbo in design
                Rectangle turboIn = new Rectangle();
                double turboInFromLeft = 1.4;
                double turboInFromTop = 1.4;
                int turboInWidth = (int) (turboOutWidth*98.3/100);
                int turboIntHeight = turboOutHeight*93/100;
                turboIn.setX(turboFromLeft+turboInFromLeft);
                turboIn.setY(turboFromTop+turboInFromTop);
                turboIn.setWidth(turboInWidth);
                turboIn.setHeight(turboIntHeight);
                turboIn.setFill(Color.WHITE);
                turboIn.setArcWidth(6);
                turboIn.setArcHeight(3);
                turboIn.setOpacity(TURBO_OPACITY);
                group.getChildren().add(turboIn);
            //Turbo Text
                String turboText = "TURBO";
                int turboTextFromLeft = turboOutWidth/2-24;
                double turboTextFromTop = 3.46;
                Label turboLabel = new Label(turboText);
                turboLabel.setTranslateX(turboFromLeft+turboTextFromLeft);
                turboLabel.setTranslateY(turboFromTop+turboTextFromTop);
                turboLabel.setFont(Font.font(get_level, FontWeight.BOLD, FontPosture.REGULAR, 13));
                turboLabel.setTextFill(Color.rgb(29, 142, 47));
                turboLabel.setOpacity(TURBO_OPACITY);
                group.getChildren().add(turboLabel);
            // Information Triangle About Turbo
                double triangleWidth = 24;
                double triangleHeight = 10;
                double triangleFromTop = HEIGHT*15/100;
                double triangleFromLeft = WIDTH*PROPORTION_OF_FOREST/100/2.6-triangleWidth/2;
                triangle = new Polygon();
                triangle.getPoints().addAll(new Double[]{
                    triangleFromLeft+(triangleWidth/2),triangleFromTop,triangleFromLeft,triangleFromTop+triangleHeight,triangleFromLeft+triangleWidth,triangleFromTop+triangleHeight
                });
                triangle.setFill(Color.WHITE);
                triangle.setOpacity(TRIANGLE_OPACITY);
                group.getChildren().add(triangle);
                
            // Information Rectangle About Turbo
                int spaceRectWidth = (int) (WIDTH*11.6/100);
                int spaceRectHeight = (int) (HEIGHT*3.12/100);
                double spaceRectFromLeft =  WIDTH*PROPORTION_OF_FOREST/100/2.6 - spaceRectWidth/2;
                double spaceRectFromTop = triangleFromTop + triangleHeight + 0.6;
                spaceRect = new Rectangle();
                spaceRect.setWidth(spaceRectWidth);
                spaceRect.setHeight(spaceRectHeight);
                spaceRect.setX(spaceRectFromLeft);
                spaceRect.setY(spaceRectFromTop);
                spaceRect.setOpacity(SPACERECT_OPACITY);
                spaceRect.setFill(Color.TRANSPARENT);
                spaceRect.setStroke(Color.WHITE);
                spaceRect.setStrokeWidth(1);
                spaceRect.setArcWidth(6);
                spaceRect.setArcHeight(3);
                group.getChildren().add(spaceRect);
                
            // Information Text About Turbo
                double infAboutTurboFromTop = 0.37;
                double infAboutTurboFromLeft = 4.54;
                String infAboutTurboText = "Press Space";
                infAboutTurboLabel = new Label(infAboutTurboText);
                infAboutTurboLabel.setTranslateY(spaceRectFromTop+infAboutTurboFromTop);
                infAboutTurboLabel.setTranslateX(spaceRectFromLeft+infAboutTurboFromLeft);
                infAboutTurboLabel.toFront();
                infAboutTurboLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 16));
                infAboutTurboLabel.setTextFill(Color.WHITE);
                infAboutTurboLabel.setOpacity(SPACERECT_OPACITY);
                group.getChildren().add(infAboutTurboLabel);
        
        // Close Game Sounds
        try{
            int closeMusicHeight = 20;
            int closeMusicFromLeft = (int) (WIDTH*96.4/100);
            int closeMusicFromTop = (int) (HEIGHT*10.4/100);
            Image closeMusic = new Image(new FileInputStream(closeMusicImg));
            closeMusicView = new ImageView(closeMusic);
            closeMusicView.setX(closeMusicFromLeft);
            closeMusicView.setY(closeMusicFromTop);
            closeMusicView.setFitHeight(closeMusicHeight);
            closeMusicView.setPreserveRatio(true);
            closeMusicView.setOpacity(0.5);
            group.getChildren().add(closeMusicView);
        }catch(Exception e){
            
        }
        closeMusicView.setOnMouseMoved((MouseEvent e) -> {
            closeMusicView.setOpacity(1);
            closeMusicView.setCursor(Cursor.HAND);
        });
        closeMusicView.setOnMouseExited((MouseEvent e) -> {
            closeMusicView.setOpacity(0.6);
        });
        
        // Close All Game Sounds
        try{
            int closeMusicHeight = 20;
            int closeMusicFromLeft = (int) (WIDTH*92/100);
            int closeMusicFromTop = (int) (HEIGHT*10.4/100);
            Image closeMusic = new Image(new FileInputStream(closeAllMusicImg));
            closeAllMusicView = new ImageView(closeMusic);
            closeAllMusicView.setX(closeMusicFromLeft);
            closeAllMusicView.setY(closeMusicFromTop);
            closeAllMusicView.setFitHeight(closeMusicHeight);
            closeAllMusicView.setPreserveRatio(true);
            closeAllMusicView.setOpacity(0.6);
            group.getChildren().add(closeAllMusicView);
        }catch(Exception e){
            
        }
        closeAllMusicView.setOnMouseMoved((MouseEvent e) -> {
            closeAllMusicView.setOpacity(1);
            closeAllMusicView.setCursor(Cursor.HAND);
        });
        closeAllMusicView.setOnMouseExited((MouseEvent e) -> {
            closeAllMusicView.setOpacity(0.5);
        });
        
        // Car Horn
            // Horn Symbol
            try{
                int hornWidth = 20;
                int hornFromLeft = (int) (WIDTH*87.7/100);
                int hornFromTop = (int) (HEIGHT*10.5/100);
                Image hornImg = new Image(new FileInputStream(hornImgSrc));
                horn = new ImageView(hornImg);
                horn.setPreserveRatio(true);
                horn.setX(hornFromLeft);
                horn.setY(hornFromTop);
                horn.setOpacity(0.5);
                horn.setFitWidth(hornWidth);
                group.getChildren().add(horn);
                
            }catch(Exception e){
            }
            
        // Difficulty Selector
            String difficultText = "Difficulty";
            Label difficultLabel = new Label(difficultText);
            int diffLabelFromLeft = WIDTH*(PROPORTION_OF_FOREST+PROPORTION_OF_ROAD)/100+35;
            int diffLabelFromTop = HEIGHT*1/100;
            difficultLabel.setTranslateX(diffLabelFromLeft);
            difficultLabel.setTranslateY(diffLabelFromTop);
            difficultLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 19));
            difficultLabel.setTextFill(Color.WHITE);
            group.getChildren().add(difficultLabel);
            diffLabelFromLeft-=5;
            
            int easyBackgroundFromLeft = -23;
            int easyBackgroundFromTop = (int)((int) HEIGHT*(4.2/100));
            int mediumBackgroundFromLeft = 23;
            int hardBackgroundFromLeft = 70;
            
            int difficultyBackgroundWidth = (int) (WIDTH*4.8/100);
            int difficultyBackgroundHeight = HEIGHT*4/100;
            
            easyBackground = new Rectangle();
            easyBackground.setX(diffLabelFromLeft+easyBackgroundFromLeft);
            easyBackground.setY(diffLabelFromTop+easyBackgroundFromTop);
            easyBackground.setWidth(difficultyBackgroundWidth);
            easyBackground.setHeight(difficultyBackgroundHeight);
            easyBackground.setFill(Color.TRANSPARENT);
            easyBackground.setStroke(Color.WHITE);
            easyBackground.setStrokeWidth(DIFFICULTY_RECTANGLE_OPACITY);
            easyBackground.setStrokeLineCap(StrokeLineCap.ROUND);
            easyBackground.setStrokeLineJoin(StrokeLineJoin.ROUND);
            
            mediumBackground = new Rectangle();
            mediumBackground.setX(diffLabelFromLeft+mediumBackgroundFromLeft);
            mediumBackground.setY(diffLabelFromTop+easyBackgroundFromTop);
            mediumBackground.setWidth(difficultyBackgroundWidth);
            mediumBackground.setHeight(difficultyBackgroundHeight);
            mediumBackground.setFill(Color.TRANSPARENT);
            mediumBackground.setStroke(Color.WHITE);
            mediumBackground.setStrokeWidth(DIFFICULTY_RECTANGLE_OPACITY);
            mediumBackground.setStrokeLineCap(StrokeLineCap.ROUND);
            mediumBackground.setStrokeLineJoin(StrokeLineJoin.ROUND);
            
            hardBackground = new Rectangle();
            hardBackground.setX(diffLabelFromLeft+hardBackgroundFromLeft);
            hardBackground.setY(diffLabelFromTop+easyBackgroundFromTop);
            hardBackground.setWidth(difficultyBackgroundWidth);
            hardBackground.setHeight(difficultyBackgroundHeight);
            hardBackground.setFill(Color.TRANSPARENT);
            hardBackground.setStroke(Color.WHITE);
            hardBackground.setStrokeWidth(DIFFICULTY_RECTANGLE_OPACITY);
            hardBackground.setStrokeLineCap(StrokeLineCap.ROUND);
            hardBackground.setStrokeLineJoin(StrokeLineJoin.ROUND);
            
            String easyText = "EASY";
            String mediumText = "MID";
            String hardText = "HARD";
            
            Label easyTextLabel = new Label(easyText);
            Label mediumTextLabel = new Label(mediumText);
            Label hardTextLabel = new Label(hardText);
            
            easyTextLabel.setTranslateX(diffLabelFromLeft+easyBackgroundFromLeft+7);
            easyTextLabel.setTranslateY(diffLabelFromTop+easyBackgroundFromTop+10);
            easyTextLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 11));
            easyTextLabel.setTextFill(Color.WHITE);
            
            mediumTextLabel.setTranslateX(diffLabelFromLeft+mediumBackgroundFromLeft+9);
            mediumTextLabel.setTranslateY(diffLabelFromTop+easyBackgroundFromTop+10);
            mediumTextLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 11));
            mediumTextLabel.setTextFill(Color.WHITE);
            
            hardTextLabel.setTranslateX(diffLabelFromLeft+hardBackgroundFromLeft+4);
            hardTextLabel.setTranslateY(diffLabelFromTop+easyBackgroundFromTop+9);
            hardTextLabel.setFont(Font.font(get_level, FontWeight.MEDIUM, FontPosture.REGULAR, 11));
            hardTextLabel.setTextFill(Color.WHITE);
                    
            group.getChildren().add(easyBackground);
            group.getChildren().add(mediumBackground);
            group.getChildren().add(hardBackground);
            group.getChildren().add(easyTextLabel);
            group.getChildren().add(mediumTextLabel);
            group.getChildren().add(hardTextLabel);
            
            
                        // Randomly Add Cars
                        rivalCarPositionY = 0;
                        
                        while(true){
                            int rivalWidth;
                            int rivalHeight;
                            int rivalType = (int)(Math.random()*3);
                                String last_rivalCarSrc;
                                if(rivalType==0){
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }else if(rivalType==1){
                                    last_rivalCarSrc = rivalCarSrc2;
                                    rivalWidth = CAR_WIDTH2;
                                    rivalHeight = CAR_HEIGHT2;
                                }else if(rivalType==2){
                                    last_rivalCarSrc = rivalCarSrc3;
                                    rivalWidth = CAR_WIDTH3;
                                    rivalHeight = CAR_HEIGHT3;
                                }else{
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }
                                try{
                                        Image rivalImg = new Image(new FileInputStream(last_rivalCarSrc));
                                        ImageView rivalCar = new ImageView(rivalImg);
                                     int rivalCarPositionX = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-rivalWidth)); 
                                     while(hitControl(carPositionX, carPositionY, rivalCarPositionX, rivalCarPositionY)){
                                        rivalCarPositionX = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-rivalWidth)); 
                                     }
                                     
                                     rivalCar.setX(rivalCarPositionX);
                                     rivalCar.setY(rivalCarPositionY);
                                     rivalCar.setFitWidth(rivalWidth);
                                     rivalCar.setFitHeight(rivalHeight);

                                     carsSources.add(rivalCar);
                                     String data = rivalCarPositionX+"-"+rivalCarPositionY+"-0-"+carType;
                                     cars.add(data);
                                     group.getChildren().add(rivalCar);

                                     rivalCarPositionY+=(SPACE_BETWEEN_CARS+CAR_HEIGHT);
                                     if(rivalCarPositionY>HEIGHT*85/100){
                                         break;
                                     }
                                }catch(Exception e){
                                }
                        }
        
        // Right Bottom Key Viewer
        upKey = new Rectangle();
        downKey = new Rectangle();
        rightKey = new Rectangle();
        leftKey = new Rectangle();
        
        int fromLeft = WIDTH*PROPORTION_OF_FOREST/100 + WIDTH*PROPORTION_OF_ROAD/100;
        int fromLeftUpKey = WIDTH*6/100;
        int fromLeftDownKey = WIDTH*6/100;
        int fromLeftLeftKey = WIDTH*2/100;
        int fromLeftRightKey = WIDTH*10/100;
        
        int fromTop = HEIGHT*80/100;
        int fromTopUpKey = 0;
        int fromTopDownKey = HEIGHT*8/100;
        int fromTopLeftKey = HEIGHT*4/100;
        int fromTopRightKey = HEIGHT*4/100;
        
        int fromTopUpKeyText = 10;
        int fromTopDownKeyText = 10;
        int fromTopLeftKeyText = 10;
        int fromTopRightKeyText = 10;
        
        int fromLeftUpKeyText = 14;
        int fromLeftDownKeyText = 6;
        int fromLeftLeftKeyText = 10;
        int fromLeftRightKeyText = 7;
        
        upKey.setWidth(KEY_WIDTH);
        upKey.setHeight(KEY_HEIGHT);
        upKey.setFill(Color.WHITE);
        upKey.setOpacity(KEY_OPACITY);
        upKey.setX(fromLeft + fromLeftUpKey);
        upKey.setY(fromTop+fromTopUpKey);
        
        downKey.setWidth(KEY_WIDTH);
        downKey.setHeight(KEY_HEIGHT);
        downKey.setFill(Color.WHITE);
        downKey.setOpacity(KEY_OPACITY);
        downKey.setX(fromLeft + fromLeftDownKey);
        downKey.setY(fromTop+fromTopDownKey);
        
        leftKey.setWidth(KEY_WIDTH);
        leftKey.setHeight(KEY_HEIGHT);
        leftKey.setFill(Color.WHITE);
        leftKey.setOpacity(KEY_OPACITY);
        leftKey.setX(fromLeft + fromLeftLeftKey);
        leftKey.setY(fromTop+fromTopLeftKey);
        
        rightKey.setWidth(KEY_WIDTH);
        rightKey.setHeight(KEY_HEIGHT);
        rightKey.setOpacity(KEY_OPACITY);
        rightKey.setFill(Color.WHITE);
        rightKey.setX(fromLeft + fromLeftRightKey);
        rightKey.setY(fromTop+fromTopRightKey);
        
        String upText = "UP";
        String downText = "DOWN";
        String leftText = "LEFT";
        String rightText = "RIGHT";
        
        Label upTextLabel = new Label(upText);
        Label downTextLabel = new Label(downText);
        Label rightTextLabel = new Label(rightText);
        Label leftTextLabel = new Label(leftText);
        
        upTextLabel.setOpacity(KEY_TEXT_OPACITY);
        downTextLabel.setOpacity(KEY_TEXT_OPACITY);
        leftTextLabel.setOpacity(KEY_TEXT_OPACITY);
        rightTextLabel.setOpacity(KEY_TEXT_OPACITY);
        
        upTextLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 10));
        downTextLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 10));
        leftTextLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 10));
        rightTextLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 10));
        
        upTextLabel.setTranslateX(fromLeft+fromLeftUpKey+fromLeftUpKeyText);
        downTextLabel.setTranslateX(fromLeft+fromLeftDownKey+fromLeftDownKeyText);
        leftTextLabel.setTranslateX(fromLeft+fromLeftLeftKey+fromLeftLeftKeyText);
        rightTextLabel.setTranslateX(fromLeft+fromLeftRightKey+fromLeftRightKeyText);
        
        upTextLabel.setTranslateY(fromTop+fromTopUpKey+fromTopUpKeyText);
        downTextLabel.setTranslateY(fromTop+fromTopDownKey+fromTopDownKeyText);
        leftTextLabel.setTranslateY(fromTop+fromTopLeftKey+fromTopLeftKeyText);
        rightTextLabel.setTranslateY(fromTop+fromTopRightKey+fromTopRightKeyText);
        
        upTextLabel.setTextFill(Color.WHITE);
        downTextLabel.setTextFill(Color.WHITE);
        leftTextLabel.setTextFill(Color.WHITE);
        rightTextLabel.setTextFill(Color.WHITE);
        
        upTextLabel.toFront();
        downTextLabel.toFront();
        leftTextLabel.toFront();
        rightTextLabel.toFront();
        
        // Close Sounds Cross
        double closeSoundFromLeft = 0;
        double closeSoundFromTop = -6;
        int closeMusicFromLeft = (int) (WIDTH*96.4/100);
        int closeMusicFromTop = (int) (HEIGHT*10.4/100);
        String closeSoundsCross = "X";
        closeSoundsLabel = new Label(closeSoundsCross);
        closeSoundsLabel.setTranslateX(closeMusicFromLeft+closeSoundFromLeft);
        closeSoundsLabel.setTranslateY(closeMusicFromTop+closeSoundFromTop);
        closeSoundsLabel.toFront();
        closeSoundsLabel.setFont(Font.font(get_level, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 24));
        closeSoundsLabel.setTextFill(Color.rgb(210, 40, 20));
        closeSoundsLabel.setOpacity(0.6);
        closeSoundsLabel.setVisible(false);
        group.getChildren().add(closeSoundsLabel);
        
        // Close All Sounds Cross
        int closeMusicFromLeft2 = (int) (WIDTH*92/100);
        int closeMusicFromTop2 = (int) (HEIGHT*10.4/100);
        closeAllSoundsLabel = new Label(closeSoundsCross);
        closeAllSoundsLabel.setTranslateX(closeMusicFromLeft2+closeSoundFromLeft);
        closeAllSoundsLabel.setTranslateY(closeMusicFromTop2+closeSoundFromTop);
        closeAllSoundsLabel.toFront();
        closeAllSoundsLabel.setFont(Font.font(get_level, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 24));
        closeAllSoundsLabel.setTextFill(Color.rgb(210, 40, 20));
        closeAllSoundsLabel.setOpacity(0.6);
        closeAllSoundsLabel.setVisible(false);
        group.getChildren().add(closeAllSoundsLabel);
        
        // When Click Mute Close All Sounds
        // When Click Music img Close Music
        closeSoundsLabel.setOnMouseClicked((MouseEvent e)->{
            if(muteSounds==0){
                muteSounds = 1;
                closeSoundsLabel.setVisible(true);
            }else{
                muteSounds = 0;
                closeSoundsLabel.setVisible(false);
            }
        });
        
        closeAllSoundsLabel.setOnMouseClicked((MouseEvent e)->{
            if(muteAllSounds==0){
                muteAllSounds = 1;
                closeAllSoundsLabel.setVisible(true);
            }else{
                muteAllSounds = 0;
                closeAllSoundsLabel.setVisible(false);
            }
        });
        
        closeMusicView.setOnMouseClicked((MouseEvent e)->{
            if(muteSounds==0){
                muteSounds = 1;
                closeSoundsLabel.setVisible(true);
            }else{
                muteSounds = 0;
                closeSoundsLabel.setVisible(false);
            }
        });
        
        
        closeAllMusicView.setOnMouseClicked((MouseEvent e)->{
            if(muteAllSounds==0){
                muteAllSounds = 1;
                closeAllSoundsLabel.setVisible(true);
            }else{
                muteAllSounds = 0;
                closeAllSoundsLabel.setVisible(false);
            }
        });
        
        group.getChildren().addAll(upKey,leftKey,rightKey,downKey);
        group.getChildren().addAll(upTextLabel,leftTextLabel,rightTextLabel,downTextLabel);
        group.getChildren().add(riscCircle);
        
        // Experince Bar Out Design
        int theExperienceOutWidth = WIDTH*PROPORTION_OF_FOREST/100*82/100;
        int theExperienceOutHeight = (int) (HEIGHT *2.32 / 100);
        int theExperienceOutFromLeft = WIDTH * PROPORTION_OF_FOREST /100/2 - theExperienceOutWidth/2 - 3;
        int theExperienceOutFromTop = (int) (HEIGHT*7.5/100);
        double theExperienceOutOpacity = 0.8;
        theExperienceOut = new Rectangle();
        theExperienceOut.setWidth(theExperienceOutWidth);
        theExperienceOut.setHeight(theExperienceOutHeight);
        theExperienceOut.setX(theExperienceOutFromLeft);
        theExperienceOut.setY(theExperienceOutFromTop);
        theExperienceOut.setFill(Color.TRANSPARENT);
        theExperienceOut.setArcHeight(3);
        theExperienceOut.setArcWidth(6);
        theExperienceOut.setStroke(Color.WHITE);
        theExperienceOut.setOpacity(theExperienceOutOpacity);
        group.getChildren().add(theExperienceOut);
        
        //Experince Bar In Text
        double theExperienceInFromLeft = 0.2;
        double theExperienceInFromTop = 0.2;
        theExperienceIn = new Rectangle();
        theExperienceIn.setWidth(0);
        theExperienceIn.setHeight(0);
        theExperienceIn.setX(theExperienceOutFromLeft+theExperienceInFromLeft);
        theExperienceIn.setY(theExperienceOutFromTop+theExperienceInFromTop);
        theExperienceIn.setFill(Color.WHITE);
        theExperienceIn.setHeight(theExperienceOutHeight*98/100);
        theExperienceIn.setOpacity(theExperienceOutOpacity);
        group.getChildren().add(theExperienceIn);
        
        // Experience Bar Text
        double experinceBarFromTop = -0.3;
        int experinceBarFromLeft = 30;
        String experienceBarText = "Experience";
        Label experienceBarLabel = new Label(experienceBarText);
        experienceBarLabel.setTranslateX(theExperienceOutFromLeft+experinceBarFromLeft);
        experienceBarLabel.setTranslateY(theExperienceOutFromTop+experinceBarFromTop);
        experienceBarLabel.setFont(Font.font(get_level, FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 13));
        experienceBarLabel.setTextFill(Color.rgb(29, 142, 47));
        experienceBarLabel.setOpacity(0.9);
        group.getChildren().add(experienceBarLabel);
        
        // Car Selector
        // Car Selector Text
        int carSelectorLabelFromLeft = (int) (WIDTH*(PROPORTION_OF_FOREST+PROPORTION_OF_ROAD)/100+WIDTH*5.4/100);
        int carSelectorLabelFromTop = HEIGHT * 37 / 100;
        String carSelectorText = "Change\nYour Car";
        Label carSelectorLabel = new Label(carSelectorText);
        carSelectorLabel.setTranslateX(carSelectorLabelFromLeft);
        carSelectorLabel.setTranslateY(carSelectorLabelFromTop);
        carSelectorLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 18));
        carSelectorLabel.setTextFill(Color.WHITE);
        carSelectorLabel.setOpacity(0.7);
        group.getChildren().add(carSelectorLabel);
        // Car Selector Background
        Rectangle carSelectorBackground = new Rectangle();
        int carSelectorBackgroundFromLeft = -3;
        int carSelectorBackgroundFromTop = 58;
        int carSelectorBackgroundWidth = 70;
        int carSelectorBackgroundHeight = 48;
        carSelectorBackground.setWidth(carSelectorBackgroundWidth);
        carSelectorBackground.setHeight(carSelectorBackgroundHeight);
        carSelectorBackground.setX(carSelectorLabelFromLeft+carSelectorBackgroundFromLeft);
        carSelectorBackground.setY(carSelectorBackgroundFromTop+carSelectorLabelFromTop);
        carSelectorBackground.setOpacity(0.15);
        carSelectorBackground.setArcHeight(2);
        carSelectorBackground.setArcWidth(2);
        carSelectorBackground.setFill(Color.WHITE);
        group.getChildren().add(carSelectorBackground);
        // Car Selector Previous Arrow
                previous = new Polygon();
                double previousWidth = 14;
                double previousHeight = 24;
                double previousFromTop = carSelectorLabelFromTop + carSelectorBackgroundFromTop + 12;
                double previousFromLeft = carSelectorBackgroundFromLeft + carSelectorLabelFromLeft - 20;
                previous.getPoints().addAll(new Double[]{
                    previousFromLeft,previousFromTop+previousHeight/2,previousFromLeft+previousWidth,previousFromTop,previousFromLeft+previousWidth,previousFromTop+previousHeight
                });
                previous.setFill(Color.WHITE);
                previous.setOpacity(0.4);
                group.getChildren().add(previous);
        // Car Selector Previous Arrow
                next = new Polygon();
                double nextWidth = 14;
                double nextHeight = 24;
                double nextFromTop = carSelectorLabelFromTop + carSelectorBackgroundFromTop + 12;
                double nextFromLeft = carSelectorBackgroundFromLeft + carSelectorLabelFromLeft + 74.6;
                next.getPoints().addAll(new Double[]{
                    nextFromLeft,nextFromTop,nextFromLeft,nextFromTop+nextHeight,nextFromLeft+nextWidth,nextFromTop+nextHeight/2
                });
                next.setFill(Color.WHITE);
                next.setOpacity(0.4);
                group.getChildren().add(next);
        // Car Selector Preview Variables
                    int previewFromLeft = (int) (WIDTH*(PROPORTION_OF_FOREST+PROPORTION_OF_ROAD)/100+WIDTH*5.5/100);
                    int previewFromTop = (int) (HEIGHT*44.42/100);
                    int previewWidth = 64;
                    double previewOpacity = 0.36;
        // Car Selector Preview-1
                try{
                    Image previewImg = new Image(new FileInputStream(carPreviewSrc));
                    preview = new ImageView(previewImg);
                    preview.setX(previewFromLeft);
                    preview.setY(previewFromTop);
                    preview.setFitWidth(previewWidth);
                    preview.setPreserveRatio(true);
                    preview.setOpacity(previewOpacity);
                    preview.setVisible(false);
                    group.getChildren().add(preview);
                    Image previewImg2 = new Image(new FileInputStream(carPreviewSrc2));
                    preview2 = new ImageView(previewImg2);
                    preview2.setX(previewFromLeft);
                    preview2.setY(previewFromTop);
                    preview2.setFitWidth(previewWidth);
                    preview2.setPreserveRatio(true);
                    preview2.setOpacity(previewOpacity);
                    preview2.setVisible(false);
                    group.getChildren().add(preview2);
                    Image previewImg3 = new Image(new FileInputStream(carPreviewSrc3));
                    preview3 = new ImageView(previewImg3);
                    preview3.setX(previewFromLeft);
                    preview3.setY(previewFromTop);
                    preview3.setFitWidth(previewWidth);
                    preview3.setPreserveRatio(true);
                    preview3.setOpacity(previewOpacity);
                    preview3.setVisible(false);
                    group.getChildren().add(preview3);
                }catch(Exception e){
                }
            // Car Selector Info Icon
                double infoWidth = 15.6;
                int infoFromLeft = carSelectorLabelFromLeft - 31;
                int infoFromTop = (int) (carSelectorLabelFromTop + HEIGHT*12.9/100);
                try{
                    Image infoImg = new Image(new FileInputStream(infoSrc));
                    info = new ImageView(infoImg);
                    info.setFitWidth(infoWidth);
                    info.setX(infoFromLeft);
                    info.setPreserveRatio(true);
                    info.setY(infoFromTop);
                    info.setOpacity(0.5);
                    group.getChildren().add(info);
                }catch(Exception e){
                }
            // Car Selector Info Text
                String infoText = "More Resistance";
                    infoLabel = new Label(infoText);
                    infoLabel.setTranslateX(infoFromLeft+17.5);
                    infoLabel.setTranslateY(infoFromTop-2.52);
                    infoLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 14));
                    infoLabel.setTextFill(Color.WHITE);
                    infoLabel.setOpacity(0.7);
                    group.getChildren().add(infoLabel);
                
        // Scene
        scene = new Scene(group,WIDTH,HEIGHT);
        
        // Press Action
        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if(!input.contains(code)){
                input.add(code);
            }
        });
        
        // Release Action
        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            if(input.contains(code)){
                input.remove(code);
            }
        });
        
        // SpeedMeter Design
            int speedMeterWidth = 110;
            int speedMeterFromLeft = WIDTH*PROPORTION_OF_FOREST/100/2 - speedMeterWidth/2 ;
            int speedMeterFromTop = HEIGHT*85/100;
        try{
            Image speedMeterImg = new Image(new FileInputStream(speedMeterSrc));
            speedMeter = new ImageView(speedMeterImg);
            speedMeter.setPreserveRatio(true);
            speedMeter.setFitWidth(speedMeterWidth);
            speedMeter.setX(speedMeterFromLeft);
            speedMeter.setY(speedMeterFromTop);
            speedMeter.setOpacity(0.8);
            group.getChildren().add(speedMeter);
        }catch(Exception e){
        }
        
        // SpeedMeter Needle
                int speedMeterNeedleHeight = 95;
                int speedMeterNeedleFromLeft = 7;
                int speedMeterNeedleFromTop = 2;
            try{
                Image speedMeterNeedleImg = new Image(new FileInputStream(speedMeterNeedleSrc));
                speedMeterNeedle = new ImageView(speedMeterNeedleImg);
                speedMeterNeedle.setPreserveRatio(true);
                speedMeterNeedle.setFitHeight(speedMeterNeedleHeight);
                speedMeterNeedle.setX(speedMeterFromLeft+speedMeterNeedleFromLeft);
                speedMeterNeedle.setY(speedMeterFromTop+speedMeterNeedleFromTop);
                speedMeterNeedle.setRotate(-141);
                group.getChildren().add(speedMeterNeedle);
            }catch(Exception e){
                e.printStackTrace();
            }
        
        // Change difficulty when click difficulty buttons
            easyBackground.setOnMouseMoved((MouseEvent e)->{
                easyBackground.setCursor(Cursor.HAND);
            });
            mediumBackground.setOnMouseMoved((MouseEvent e)->{
                mediumBackground.setCursor(Cursor.HAND);
            });
            hardBackground.setOnMouseMoved((MouseEvent e)->{
                hardBackground.setCursor(Cursor.HAND);
            });
            easyTextLabel.setOnMouseMoved((MouseEvent e)->{
                easyTextLabel.setCursor(Cursor.HAND);
            });
            mediumTextLabel.setOnMouseMoved((MouseEvent e)->{
                mediumTextLabel.setCursor(Cursor.HAND);
            });
            hardTextLabel.setOnMouseMoved((MouseEvent e)->{
                hardTextLabel.setCursor(Cursor.HAND);
            });
        easyBackground.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 0;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 0;
            }
        });
        mediumBackground.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 1;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 1;
            }
        });
        hardBackground.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 2;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 2;
            }
        });
        easyTextLabel.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 0;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 0;
            }
        });
        mediumTextLabel.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 1;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 1;
            }
        });
        hardTextLabel.setOnMouseClicked((MouseEvent e) -> {
            try{
                difficulty = 2;
                int isThereDataWeNeed = 0;
                Scanner s = new Scanner(file);
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    if(line.contains("difficulty=")){
                        isThereDataWeNeed = 1;
                    }
                }
                if(isThereDataWeNeed==0){
                    writer = new BufferedWriter(new FileWriter(file));
                    PrintWriter outer = new PrintWriter(writer);
                    outer.println("difficulty="+difficulty);
                    outer.close();
                }else{
                   s = new Scanner(file);
                   while(s.hasNextLine()){
                       String line = s.nextLine();
                       holder.add(line);
                   }
                   // Clear file
                   writer = new BufferedWriter(new FileWriter(file));
                   writer.write("");
                   writer.close();
                   
                   writer = new BufferedWriter(new FileWriter(file));
                   PrintWriter outer = new PrintWriter(writer);
                   
                   // Write again
                   for(String line:holder){
                       outer.println(line);
                   }
                   
                   writer.close();
                   outer.close();
                   
                }
            }catch(Exception expception){
                difficulty = 2;
            }
        });
        
        // Output 
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                
                // Game Speed
                double acceleration = 0.04 * speed_rate;
                gameSpeedTop = (4+level_live) * speed_rate;
                
                // Speed Up Control
                if(endedGame == 0){
                    if(input.contains("UP")){
                       brakeSound.stop();
                       brakeSound.setOnEndOfMedia(new Runnable() {

                           @Override
                           public void run() {
                               brakeSound.seek(Duration.ZERO);
                           }
                       });
                       if(gameSpeed<gameSpeedTop){
                           if(gameSpeed<(gameSpeedTop)*90/100){
                               if(muteAllSounds==0){
                                    speedUpSoundPlayer.play();
                                }
                                constantSpeedPlayer.stop();
                           }else{
                                speedUpSoundPlayer.stop();
                                if(muteAllSounds==0){
                                    constantSpeedPlayer.play();
                                }
                           }
                           gameSpeed+=acceleration;
                           speedUpSoundPlayer.setStartTime(Duration.ZERO);
                           int indicator = (int) gameSpeed * 20;
                            carSpeed.setText(String.valueOf(indicator));
                       }else{
                            int indicator = (int) gameSpeed * 20;
                            carSpeed.setText(String.valueOf(indicator));
                            speedUpSoundPlayer.stop();
                            if(muteAllSounds==0){
                                constantSpeedPlayer.play();
                            }
                       }
                    }else{
                        speedUpSoundPlayer.stop();
                        if(gameSpeed>0){
                            gameSpeed-=acceleration;
                            if(gameSpeed<0){
                                gameSpeed=0;
                            }
                           int indicator = (int) gameSpeed * 20;
                            carSpeed.setText(String.valueOf(indicator));
                        }
                    }
                    
                    if(endedGame == 0){
                        if(input.contains("UP")){
                            if(triangle.visibleProperty().getValue()){
                                triangle.setVisible(false);
                                spaceRect.setVisible(false);
                                infAboutTurboLabel.setVisible(false);
                            }
                        }
                    }
                    
                    if(endedGame == 0){
                        if(input.contains("F")){
                            horn.setOpacity(0.9);
                            hornPlayer.play();
                           
                        }else{
                            horn.setOpacity(0.5);
                            hornPlayer.stop();
                        }
                    }
                    
                    // Get back nitro speed
                    if(endedGame == 0){
                        if(gameSpeed>=gameSpeedTop){
                            gameSpeed-=acceleration*3;
                            if(gameSpeed<0){
                                gameSpeed=0;
                            }
                            int indicator = (int) gameSpeed * 20;
                            carSpeed.setText(String.valueOf(indicator));
                            // Overspeed Warning
                        }else{
                            // Remove Overspeed Warning
                        }
                    }
                    
                    if(input.contains("SPACE")){
                        if(turbo>0 && gameSpeed>0){
                            if(muteAllSounds==0){
                                nitroPlayer.play();
                            }
                            if(gameSpeed<gameSpeedTop){
                                int turboInWidth = (int) ((turboOutWidth*98.3/100)*turbo/100);
                                turboIn.setWidth(turboInWidth);
                                turbo-=2;
                                gameSpeed+=acceleration*8;
                            }else{
                                int turboInWidth = (int) ((turboOutWidth*98.3/100)*turbo/100);
                                turboIn.setWidth(turboInWidth);
                                turbo-=4;
                                gameSpeed+=acceleration*8;
                            }
                            nitroPlayer.seek(Duration.ZERO);
                        }
                    }
                    
                    if(input.contains("DOWN")){
                        if(gameSpeed>0 && (!(gameSpeed<0))){
                            gameSpeed-=acceleration;
                            if(gameSpeed<0){
                                gameSpeed=0;
                            }
                        }
                    }else{
                        brakeSound.stop();
                    }

                    if(input.contains("RIGHT")){
                        if(gameSpeed>0){
                            carPositionX+=HORIZONTAL_VELOCITY;
                            if(carPositionX>(WIDTH*PROPORTION_OF_FOREST/100 + WIDTH*PROPORTION_OF_ROAD/100 - CAR_WIDTH)){
                                carPositionX = WIDTH*PROPORTION_OF_FOREST/100 + WIDTH*PROPORTION_OF_ROAD/100 - CAR_WIDTH;
                            }
                            ourCar.setX(carPositionX);
                            group.setRotate(0.08);
                        }
                    }else{
                        if(!input.contains("LEFT")){
                            group.setRotate(0);
                        }
                    }
                    if(input.contains("LEFT")){
                        if(gameSpeed>0){
                            carPositionX-=HORIZONTAL_VELOCITY;
                            if(carPositionX<WIDTH*PROPORTION_OF_FOREST/100){
                                carPositionX = WIDTH*PROPORTION_OF_FOREST/100;
                            }
                            ourCar.setX(carPositionX);
                            group.setRotate(-0.08);
                        }
                    }else{
                        if(!input.contains("RIGHT")){
                            group.setRotate(0);
                        }
                    }
                }
                
                // When press keys blink up Key Buttons
                if(input.contains("LEFT")){
                    leftKey.setOpacity(PRESS_OPACITY);
                    ourCar.setRotate(-25);
                }else{
                    leftKey.setOpacity(KEY_OPACITY);
                    ourCar.setRotate(0);
                }
                if(input.contains("RIGHT")){
                    rightKey.setOpacity(PRESS_OPACITY);
                    ourCar.setRotate(25);
                }else{
                    ourCar.setRotate(0);
                    rightKey.setOpacity(KEY_OPACITY);
                }
                if(input.contains("UP")){
                    upKey.setOpacity(PRESS_OPACITY);
                }else{
                    upKey.setOpacity(KEY_OPACITY);
                }
                if(input.contains("DOWN")){
                    downKey.setOpacity(PRESS_OPACITY);
                    if(gameSpeed>0){
                        if(muteAllSounds==0){
                            brakeSound.play();
                        }
                    }else{
                        brakeSound.stop();
                    }
                }else{
                    downKey.setOpacity(KEY_OPACITY);
                }
                
                // Restart everything for new game when press Enter
                if(input.contains("ENTER")){
                    if(endedGame==1){
                        brakeSound.stop();
                        gameSpeed = 0;
                        endedGame = 0;
                        score_live = 0;
                        experience = 0;
                        level_live = 1; 
                        turbo = 100;
                        triangle.setVisible(true);
                        spaceRect.setVisible(true);
                        infAboutTurboLabel.setVisible(true);
                        int turboInWidth = (int) ((turboOutWidth*98.3/100)*turbo/100);
                        turboIn.setWidth(turboInWidth);
                        HORIZONTAL_VELOCITY*=movement_rate;
                        
                        // Delete All Cars
                        if(carsSources.size()>0){
                            Iterator iterator = cars.iterator();
                            while(iterator.hasNext()){
                                String getData = iterator.next().toString();
                                int index = cars.indexOf(getData);
                                iterator.remove();
                                ImageView o = (ImageView) carsSources.get(index);
                                carsSources.remove(index);
                                group.getChildren().remove(o);
                            }
                        }
                        
                        // Randomly Add Cars
                        rivalCarPositionY = 0;
                        while(true){
                            int rivalWidth;
                            int rivalHeight;
                            int rivalType = (int)(Math.random()*3);
                                String last_rivalCarSrc;
                                if(rivalType==0){
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }else if(rivalType==1){
                                    last_rivalCarSrc = rivalCarSrc2;
                                    rivalWidth = CAR_WIDTH2;
                                    rivalHeight = CAR_HEIGHT2;
                                }else if(rivalType==2){
                                    last_rivalCarSrc = rivalCarSrc3;
                                    rivalWidth = CAR_WIDTH3;
                                    rivalHeight = CAR_HEIGHT3;
                                }else{
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }
                                try{
                                        Image rivalImg = new Image(new FileInputStream(last_rivalCarSrc));
                                        ImageView rivalCar = new ImageView(rivalImg);

                                     int rivalCarPositionX = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-rivalWidth));
                                     while(hitControl(carPositionX, carPositionY, rivalCarPositionX, rivalCarPositionY)){
                                        rivalCarPositionX = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-rivalWidth)); 
                                     }
                                     rivalCar.setX(rivalCarPositionX); 
                                     rivalCar.setY(rivalCarPositionY);
                                     rivalCar.setFitWidth(rivalWidth);
                                     rivalCar.setFitHeight(rivalHeight);

                                     carsSources.add(rivalCar);
                                     String data = rivalCarPositionX+"-"+rivalCarPositionY+"-0-"+carType;
                                     cars.add(data);
                                     group.getChildren().add(rivalCar);

                                     rivalCarPositionY+=(SPACE_BETWEEN_CARS+CAR_HEIGHT);
                                     if(rivalCarPositionY>HEIGHT*85/100){
                                         break;
                                     }
                                }catch(Exception e){
                                }
                        }
                        
                        // Difficulty Settings
                        if(difficulty==0){
                            hard_rate = 0.8;
                        }else if(difficulty==1){
                            hard_rate = 1.2;
                        }else if(difficulty==2){
                            hard_rate = 1.32;
                        }
                        SPACE_BETWEEN_CARS = SPACE_BETWEEN_CARS_CONSTANT / hard_rate;
                        
                        // Update Car
                        if(carType==0){
                            try{
                            ourCar.setImage(new Image(new FileInputStream(ourCarSrc)));
                            ourCar.setFitWidth(OUR_CAR_WIDTH);
                            ourCar.setFitHeight(OUR_CAR_HEIGHT);
                            }catch(Exception e){
                            }
                        }else if(carType==1){
                            try{
                            ourCar.setImage(new Image(new FileInputStream(ourCarSrc2)));
                            ourCar.setFitWidth(OUR_CAR2_WIDTH);
                            ourCar.setFitHeight(OUR_CAR2_HEIGHT);
                            }catch(Exception e){
                            }
                        }else if(carType==2){
                            try{
                            ourCar.setImage(new Image(new FileInputStream(ourCarSrc3)));
                            ourCar.setFitWidth(OUR_CAR3_WIDTH);
                            ourCar.setFitHeight(OUR_CAR3_HEIGHT);
                            }catch(Exception e){
                            }
                        }
                        
                        // Delete All Muds And Oils
                        if(mudList.size()>0){
                            Iterator iterator = mudSource.iterator();
                            while(iterator.hasNext()){
                                ImageView getData = (ImageView)iterator.next();
                                group.getChildren().remove(getData);
                            }
                            mudList.clear();
                        }
                        if(oilList.size()>0){
                            Iterator iterator = oilSource.iterator();
                            while(iterator.hasNext()){
                                ImageView getData = (ImageView)iterator.next();
                                group.getChildren().remove(getData);
                            }
                            oilList.clear();
                        }
                        
                        carPositionX = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-CAR_WIDTH));
                        ourCar.setX(carPositionX);
                        
                    }
                }
                
                
                
                // Add Random Created Wet and Mud Floor
                // Add Mud Floor
                
                if(gameSpeed>0){
                    int mudRandom = (int) (Math.random()*100);
                    if(mudRandom==7){
                        int mudRandom2 = (int)(Math.random()*100);
                        if(mudRandom2<10){
                            try{
                                Image mudImg = new Image(new FileInputStream(mudSrc));
                                mud = new ImageView(mudImg);
                                int mudWidth = MUD_WIDTH;
                                int mudFromTop = 0;
                                int mudFromLeft = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-mudWidth));
                                while(true){
                                    if(cars.size()>0){
                                        Iterator iterator = cars.iterator();
                                        while(iterator.hasNext()){
                                            String getData = iterator.next().toString();
                                            String [] splitedData = getData.split("-");
                                            int rivalX = Integer.parseInt(splitedData[0]);
                                            int rivalY = Integer.parseInt(splitedData[1]);
                                            if(hitControl(mudFromLeft, mudFromTop, rivalX , rivalY)){
                                                mudFromLeft = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-mudWidth));
                                            }
                                        }
                                        break;
                                    }else{
                                        break;
                                    }
                                }
                                mud.setFitWidth(mudWidth);
                                mud.setX(mudFromLeft);
                                mud.setY(mudFromTop);
                                mud.setPreserveRatio(true);
                                group.getChildren().add(mud);
                                String mudData = mudFromLeft+"-"+mudFromTop;
                                mudList.add(mudData);
                                mudSource.add(mud);
                            }catch(Exception e){
                            }
                        }
                    }
                }
                
                // Add Oil
                if(gameSpeed>0){
                    int oilRandom = (int) (Math.random()*100);
                    if(oilRandom==7){
                        int oilRandom2 = (int)(Math.random()*100);
                        if(oilRandom2<10){
                            try{
                                Image oilImg = new Image(new FileInputStream(oilSrc));
                                oil = new ImageView(oilImg);
                                int oilFromLeft = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-OIL_WIDTH));
                                int oilFromTop = 0;
                                while(true){
                                    if(cars.size()>0){
                                        Iterator iterator = cars.iterator();
                                        while(iterator.hasNext()){
                                            String getData = iterator.next().toString();
                                            String [] splitedData = getData.split("-");
                                            int rivalX = Integer.parseInt(splitedData[0]);
                                            int rivalY = Integer.parseInt(splitedData[1]);
                                            if(hitControl(oilFromLeft, oilFromTop, rivalX , rivalY)){
                                                oilFromLeft = WIDTH*PROPORTION_OF_FOREST/100 + (int)(Math.random()*(WIDTH*PROPORTION_OF_ROAD/100-OIL_WIDTH));
                                            }
                                        }
                                        break;
                                    }else{
                                        break;
                                    }
                                }
                                oil.setFitWidth(OIL_WIDTH);
                                oil.setPreserveRatio(true);
                                oil.setX(oilFromLeft);
                                group.getChildren().add(oil);
                                String oilData = oilFromLeft+"-"+oilFromTop;
                                oilList.add(oilData);
                                oilSource.add(oil);
                            }catch(Exception e){
                            }
                        }
                    }
                }
                
                
                
                // Delete All Mud
                if(mudSource.size()>0){
                Iterator iterator = mudSource.iterator();
                while(iterator.hasNext()){
                    ImageView o = (ImageView) iterator.next();
                    group.getChildren().remove(o);
                    iterator.remove();
                }
                }
                
                // Delete All Oil
                if(oilSource.size()>0){
                Iterator iterator = oilSource.iterator();
                while(iterator.hasNext()){
                    ImageView o = (ImageView) iterator.next();
                    group.getChildren().remove(o);
                    iterator.remove();
                }
                }
                
                // Sketch and Mud animation
                if(endedGame==0){
                    if(mudList.size()>0){
                        Iterator iterator = mudList.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int mudFromLeft= Integer.parseInt(splitedData[0]);
                            int mudFromTop = Integer.parseInt(splitedData[1]);
                            mudFromTop+=gameSpeed;
                                int index = mudList.indexOf(getData);
                            if(mudFromTop>HEIGHT){
                                iterator.remove();
                            }else{
                                String new_data = mudFromLeft+"-"+mudFromTop;
                                try{
                                    ImageView element = new ImageView(new Image(new FileInputStream(mudSrc)));
                                    element.setY(mudFromTop);
                                    element.setX(mudFromLeft);
                                    element.setFitWidth(100);
                                    element.setFitHeight(100);
                                    group.getChildren().add(element);
                                    mudSource.add(element);
                                    mudList.set(index, new_data);
                                }catch(Exception e){
                                }
                            }
                        }
                    }
                }
                
                // Sketch and Oil animation
                if(endedGame==0){
                    if(oilList.size()>0){
                        Iterator iterator = oilList.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int oilFromLeft= Integer.parseInt(splitedData[0]);
                            int oilFromTop = Integer.parseInt(splitedData[1]);
                            oilFromTop+=gameSpeed;
                                int index = oilList.indexOf(getData);
                            if(oilFromTop>HEIGHT){
                                iterator.remove();
                            }else{
                                String new_data = oilFromLeft+"-"+oilFromTop;
                                try{
                                    ImageView element = new ImageView(new Image(new FileInputStream(oilSrc)));
                                    element.setY(oilFromTop);
                                    element.setX(oilFromLeft);
                                    element.setFitWidth(OIL_WIDTH);
                                    element.setPreserveRatio(true);
                                    group.getChildren().add(element);
                                    oilSource.add(element);
                                    oilList.set(index, new_data);
                                }catch(Exception e){
                                }
                            }
                        }
                    }
                }
                
                // Animation of road lines
                // Create If Scene Need
                if(endedGame==0){
                    int need = 0;
                    Iterator iterator = roadLines.iterator();
                    while(iterator.hasNext()){
                        String getData = iterator.next().toString();
                        String [] splitedData = getData.split("-");
                        int fromLeft = Integer.parseInt(splitedData[0]);
                        int fromTop = Integer.parseInt(splitedData[1]);
                        if(fromTop<SPACE_BETWEEN_LINES+ROAD_LINE_HEIGHT){
                            need+=1;
                            break;
                        }
                    }
                    if(need==0){
                            Rectangle o = new Rectangle();
                            int roadLeft = (WIDTH*PROPORTION_OF_FOREST/100)+(WIDTH*PROPORTION_OF_ROAD/200)-ROAD_LINE_WIDTH/2;
                            o.setWidth(ROAD_LINE_WIDTH);
                            o.setHeight(ROAD_LINE_HEIGHT);
                            o.setX(roadLeft);
                            o.setOpacity(ROAD_LINE_OPACITY);
                            o.setY(0);
                            group.getChildren().add(o);
                            roadLinesSources.add(o);
                            String data = roadLeft+"-0";
                            roadLines.add(data);
                    }
                }
                
                // Delete If Out Of Scene
                if(endedGame==0){
                    Iterator iterator = roadLines.iterator();
                    while(iterator.hasNext()){
                        String getData = iterator.next().toString();
                        String [] splitedData = getData.split("-");
                        int fromLeft = Integer.parseInt(splitedData[0]);
                        int fromTop = Integer.parseInt(splitedData[1]);
                        int index = roadLines.indexOf(getData);
                        fromTop+=gameSpeed;
                        if(fromTop>HEIGHT){
                            iterator.remove();
                            Rectangle o = (Rectangle) roadLinesSources.get(index);
                            group.getChildren().remove(o);
                            roadLinesSources.remove(index);
                        }else{
                            String new_data = fromLeft+"-"+fromTop;
                            Rectangle o = (Rectangle) roadLinesSources.get(index);
                            o.setY(fromTop);
                            roadLines.set(index, new_data);
                            
                        }
                    }
                }
                
                // Sketch car again to z-index
                if(endedGame == 0){
                    group.getChildren().remove(ourCar);
                    group.getChildren().add(ourCar);
                }
                
                if(input.contains("RIGHT")){
                    ourCar.setRotate(20);
                }else if(input.contains("LEFT")){
                    ourCar.setRotate(-20);
                }else{
                    ourCar.setRotate(0);
                }
                
                // Update Scoreboard
                String boardText = "Score: "+score_live;
                score.setText(boardText);
                
                String levelText = "Level: "+level_live;
                level.setText(levelText);
                
                // Level Up Control
                int needExperienceToUpLevel = (level_live*level_live) + 5;
                if(experience >= needExperienceToUpLevel){
                    level_live+=1;
                }
                
                // Crash Control
                if(cars.size() > 0 && endedGame == 0){
                    Iterator iterator = cars.iterator();
                    while(iterator.hasNext()){
                        String getData = iterator.next().toString();
                        String [] splitedData = getData.split("-");
                        int rivalCarPositionX = Integer.parseInt(splitedData[0]);
                        int rivalCarPositionY = Integer.parseInt(splitedData[1]);
                        int rivalCarType = Integer.parseInt(splitedData[3]);
                        int PROCESS_CAR_WIDTH;
                        int PROCESS_CAR_HEIGHT;
                        if(rivalCarType==0){
                            PROCESS_CAR_WIDTH = CAR_WIDTH;
                            PROCESS_CAR_HEIGHT = CAR_HEIGHT;
                        }else if(rivalCarType==1){
                            PROCESS_CAR_WIDTH = CAR_WIDTH2;
                            PROCESS_CAR_HEIGHT = CAR_HEIGHT2;
                        }else if(rivalCarType==2){
                            PROCESS_CAR_WIDTH = CAR_WIDTH3;
                            PROCESS_CAR_HEIGHT = CAR_HEIGHT3;
                        }else{
                            PROCESS_CAR_WIDTH = CAR_WIDTH;
                            PROCESS_CAR_HEIGHT = CAR_HEIGHT;
                        }
                        if(carPositionX>rivalCarPositionX && carPositionX<rivalCarPositionX+PROCESS_CAR_WIDTH){
                            if(carPositionY>rivalCarPositionY && carPositionY<rivalCarPositionY+PROCESS_CAR_HEIGHT){
                                endedGame = 1;
                                brakeSound.stop();
                                speedUpSoundPlayer.stop();
                                if(muteAllSounds==0){
                                    carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }else if(carPositionY+PROCESS_CAR_HEIGHT>rivalCarPositionY && carPositionY+PROCESS_CAR_HEIGHT<rivalCarPositionY+PROCESS_CAR_HEIGHT){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }
                        }else if(carPositionX+PROCESS_CAR_WIDTH>rivalCarPositionX && carPositionX+PROCESS_CAR_WIDTH<rivalCarPositionX+PROCESS_CAR_WIDTH){
                            if(carPositionY>rivalCarPositionY && carPositionY<rivalCarPositionY+CAR_HEIGHT){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }else if(carPositionY+PROCESS_CAR_HEIGHT > rivalCarPositionY && carPositionY+PROCESS_CAR_HEIGHT<rivalCarPositionY+PROCESS_CAR_HEIGHT){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }
                        }else if(carPositionY+PROCESS_CAR_HEIGHT>rivalCarPositionY && carPositionY+PROCESS_CAR_HEIGHT<rivalCarPositionY+PROCESS_CAR_HEIGHT){
                            if(carPositionX>rivalCarPositionX && carPositionX<rivalCarPositionX+PROCESS_CAR_WIDTH){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }else if(carPositionX+PROCESS_CAR_WIDTH>rivalCarPositionX && carPositionX<rivalCarPositionX+PROCESS_CAR_WIDTH){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }
                        }else if(carPositionY>rivalCarPositionY && carPositionY<rivalCarPositionY+PROCESS_CAR_HEIGHT){
                            if(carPositionX>rivalCarPositionX && carPositionX<rivalCarPositionX+PROCESS_CAR_WIDTH){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }else if(carPositionX+PROCESS_CAR_WIDTH > rivalCarPositionX && carPositionX+PROCESS_CAR_WIDTH < rivalCarPositionX+PROCESS_CAR_WIDTH){
                                endedGame = 1;
                                brakeSound.stop();
                                if(muteAllSounds==0){
                                carCrashPlayer.play();
                                }
                                carCrashPlayer.seek(Duration.ZERO);
                            }
                        }
                    }
                }
                
                if(endedGame == 1){
                    try {
                        Scanner s = new Scanner(file);
                        while(s.hasNextLine()){
                            String line = s.nextLine();
                            if(line.contains("data")){
                                String [] splitedData = line.split("-");
                                int getHighScore = Integer.parseInt(splitedData[1]);
                                if(score_live>getHighScore){
                                    HighScore = score_live;
                                    writer = new BufferedWriter(new FileWriter(file));
                                    writer.write("data-"+HighScore);
                                    writer.close();
                                }
                            }else{
                                writer = new BufferedWriter(new FileWriter(file));
                                HighScore = score_live;
                                writer.write("data-"+score_live);
                                writer.close();
                            }
                        }
                    } catch (Exception e) {
                        HighScore = 0;
                    }
                    
                    
                    // Create background to alert
                    background = new Rectangle();
                    int backgroundWidth = WIDTH*76/100;
                    int backgroundHeight = HEIGHT *48/100;
                    background.setX(WIDTH*PROPORTION_OF_FOREST/100+WIDTH*PROPORTION_OF_ROAD/200-backgroundWidth/2);
                    background.setY(HEIGHT*5/100);
                    background.setWidth(backgroundWidth);
                    background.setHeight(backgroundHeight);
                    background.setFill(Color.WHITE);
                    background.setOpacity(0.02);
                    group.getChildren().add(background);
                    
                    // Create text to inform at end of the game
                    int firstLabelHeight = HEIGHT*8/100;
                    int space_between_labels = HEIGHT*10/100;
                    String gameOverText = "GAME OVER !";
                    String scoreText = "Your Score: "+score_live;
                    String higScoreText = "Highest Score: "+HighScore;
                    String againText = "Press ENTER to restart !";
                    gameOverLabel = new Label(gameOverText);
                    scoreLabel = new Label(scoreText);
                    againLabel = new Label(againText);
                    highScoreLabel = new Label(higScoreText);
                    gameOverLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 30));
                    gameOverLabel.setTranslateX(WIDTH/2-100);
                    gameOverLabel.setTranslateY(firstLabelHeight);
                    gameOverLabel.setTextFill(Color.GREEN);
                    gameOverLabel.toFront();
                    scoreLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 24));
                    int minusWidth;
                    if(score_live>99){
                        minusWidth = 85;
                    }else if(score_live>9){
                        minusWidth = 84;
                    }else{
                        minusWidth = 81;
                    }
                    scoreLabel.setTranslateX(WIDTH/2-minusWidth);
                    scoreLabel.setTranslateY(firstLabelHeight + gameOverLabel.getHeight() + space_between_labels*2);
                    scoreLabel.setTextFill(Color.GREEN);
                    scoreLabel.toFront();
                    againLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 28));
                    againLabel.setTranslateX(WIDTH/2-144);
                    againLabel.setTranslateY(firstLabelHeight + space_between_labels*3);
                    againLabel.setTextFill(Color.GREEN);
                    againLabel.toFront();
                    highScoreLabel.setFont(Font.font(get_level, FontWeight.NORMAL, FontPosture.REGULAR, 22));
                    if(HighScore>99){
                        minusWidth = 99; 
                    }else if(HighScore>9){
                        minusWidth = 93;
                    }else{
                        minusWidth = 87;
                    }
                    highScoreLabel.setTranslateX(WIDTH/2-minusWidth);
                    highScoreLabel.setTranslateY(firstLabelHeight+ space_between_labels);
                    highScoreLabel.setTextFill(Color.GREEN);
                    highScoreLabel.toFront();
                    group.getChildren().addAll(gameOverLabel,scoreLabel,againLabel,highScoreLabel);
                    labels.add(background);
                    labels.add(scoreLabel);
                    labels.add(againLabel);
                    labels.add(gameOverLabel);
                    labels.add(highScoreLabel);
                }else{
                    if(labels.size()>0){
                        Iterator iterator = labels.iterator();
                        while(iterator.hasNext()){
                            Object o = (Object) iterator.next();
                            group.getChildren().remove(o);
                            iterator.remove();
                        }
                    }
                }
                
                // Crash Warning
                if(endedGame == 0){
                    Iterator iterator = cars.iterator();
                    while(iterator.hasNext()){
                        String getData = iterator.next().toString();
                        String [] splitedData = getData.split("-");
                        int x_coordinate = Integer.parseInt(splitedData[0]);
                        int y_coordinate = Integer.parseInt(splitedData[1]);
                        if(y_coordinate > WIDTH * 75 / 100){
                            int isPassed = Integer.parseInt(splitedData[2]);
                            boolean crashRisk = riskControl(carPositionX, carPositionY, x_coordinate, y_coordinate, RISK_INTERVAL);
                            if(crashRisk){
                                riscCircle.setVisible(true);
                                riscCircleLabel.setVisible(true);
                            }else{
                                riscCircle.setVisible(false);
                                riscCircleLabel.setVisible(false);
                            }
                        }
                        
                    }
                }
                
                // Car Speed Bug Fix
                if(gameSpeed*20>100){
                    double carSpeedFromLeft = WIDTH * 7.66 / 100;
                    carSpeed.setTranslateX(carSpeedFromLeft);
                }else if(gameSpeed*20>19){
                    double carSpeedFromLeft = WIDTH * 8.2 / 100;
                    carSpeed.setTranslateX(carSpeedFromLeft);
                }else{
                    double carSpeedFromLeft = WIDTH * 8.4 / 100;
                    carSpeed.setTranslateX(carSpeedFromLeft);
                }
                
                // Check Sound Setting Difference
                if(muteSounds==1){
                    backSoundPlayer.stop();
                }else{
                    backSoundPlayer.play();
                }
                if(muteAllSounds==1){
                    speedUpSoundPlayer.stop();
                    backSoundPlayer.stop();
                    brakeSound.stop();
                    carCrashPlayer.stop();
                    nitroPlayer.stop();
                    constantSpeedPlayer.stop();
                }
                
                // Check Difficulty Rectangle Background
                easyBackground.setFill(Color.TRANSPARENT);
                easyBackground.setOpacity(1);
                mediumBackground.setFill(Color.TRANSPARENT);
                mediumBackground.setOpacity(1);
                hardBackground.setFill(Color.TRANSPARENT);
                hardBackground.setOpacity(1);
                if(difficulty==0){
                    easyBackground.setFill(Color.GREY);
                    easyBackground.setOpacity(0.5);
                }else if(difficulty == 1){
                    mediumBackground.setFill(Color.GREY);
                    mediumBackground.setOpacity(0.5);
                }else if(difficulty == 2){
                    hardBackground.setFill(Color.GREY);
                    hardBackground.setOpacity(0.5);
                }
                
                // Mud Touch Control
                if(endedGame==0){
                    if(mudList.size()>0){
                        Iterator iterator = mudList.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int mudX = Integer.parseInt(splitedData[0]);
                            int mudY = Integer.parseInt(splitedData[1]);
                            int side = (int) Math.round(Math.random()*1);
                            if(riskControl(mudX, mudY, carPositionX, carPositionY,1)){
                                    if(gameSpeed>0){
                                        gameSpeed-=(gameSpeed*1.88/100/resistent_rate);
                                        if(gameSpeed<0){
                                            gameSpeed=0;
                                        }
                                    }
                            }
                            break;
                        }
                    }
                }
                
                
                // Oil Touch Control
                if(endedGame==0){
                    if(oilList.size()>0){
                        Iterator iterator = oilList.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int oilX = Integer.parseInt(splitedData[0]);
                            int oilY = Integer.parseInt(splitedData[1]);
                            int side = (int) Math.round(Math.random()*1);
                            if(riskControl(oilX, oilY, carPositionX, carPositionY,1)){
                                    if(carPositionX>WIDTH*PROPORTION_OF_FOREST/100 && carPositionX<WIDTH*(PROPORTION_OF_FOREST+PROPORTION_OF_ROAD)-OUR_CAR_WIDTH){
                                            if(gameSpeed>0){
                                                if(side==0){
                                                    carPositionX-=gameSpeed*3.2/100/resistent_rate;
                                                    ourCar.setX(carPositionX);
                                                }else{
                                                    carPositionX+=gameSpeed*3.2/100/resistent_rate;
                                                    ourCar.setX(carPositionX);
                                                }
                                            }
                                    }
                            }
                            break;
                        }
                    }
                }
                
                // Add Rival Cars
                if(endedGame==0){
                    int createable = 0;
                    if(cars.size()>0){
                        Iterator iterator = cars.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int rivalX = Integer.parseInt(splitedData[0]);
                            int rivalY = Integer.parseInt(splitedData[1]);
                            int rivalPointed = Integer.parseInt(splitedData[2]);
                            if(rivalY<SPACE_BETWEEN_CARS){
                                createable+=1;
                                break;
                            }
                        }
                    }
                    if(createable==0){
                        int coincidence = (int) (Math.random()*100);
                        if(coincidence<(10*hard_rate)){
                            int rivalWidth;
                            int rivalHeight;
                            int rivalX = (int) (WIDTH*PROPORTION_OF_FOREST/100) + (int) (Math.random()*(WIDTH*PROPORTION_OF_ROAD/100*90/100));
                            int rivalY = 0;
                            try{
                                int rivalType = (int)(Math.random()*3);
                                String last_rivalCarSrc;
                                if(rivalType==0){
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }else if(rivalType==1){
                                    last_rivalCarSrc = rivalCarSrc2;
                                    rivalWidth = CAR_WIDTH2;
                                    rivalHeight = CAR_HEIGHT2;
                                }else if(rivalType==2){
                                    last_rivalCarSrc = rivalCarSrc3;
                                    rivalWidth = CAR_WIDTH3;
                                    rivalHeight = CAR_HEIGHT3;
                                }else{
                                    last_rivalCarSrc = rivalCarSrc;
                                    rivalWidth = CAR_WIDTH;
                                    rivalHeight = CAR_HEIGHT;
                                }
                                Image rivalImg = new Image(new FileInputStream(last_rivalCarSrc));
                                ImageView rival = new ImageView(rivalImg);
                                rival.setFitWidth(rivalWidth);
                                rival.setFitHeight(rivalHeight);
                                rival.setX(rivalX);
                                rival.setY(rivalY);
                                if(rivalType==1){
                                    rival.setOpacity(CAR_OPACITY*75/100);
                                }else{
                                    rival.setOpacity(CAR_OPACITY);
                                }
                                group.getChildren().add(rival);
                                carsSources.add(rival);
                                String new_data = rivalX+"-"+rivalY+"-0-"+rivalType;
                                cars.add(new_data);
                            }catch(Exception e){
                            }
                        }
                    }
                }
                
                // Rival Car Animation
                if(endedGame==0){
                    if(carsSources.size()>0){
                        Iterator iterator = cars.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int rivalX = Integer.parseInt(splitedData[0]);
                            int rivalY = Integer.parseInt(splitedData[1]);
                            int isScored = Integer.parseInt(splitedData[2]);
                            int carType = Integer.parseInt(splitedData[3]);
                            int index = cars.indexOf(getData);
                            rivalY+=gameSpeed;
                            if(rivalY > HEIGHT*87/100){
                            if(isScored == 0){
                                isScored = 1;
                                score_live += level_live;
                                experience += 1;
                                // INCREASE TURBO
                                if(experience%2==0){
                                    if(turbo<100){
                                        turbo += 15/hard_rate;
                                        if(turbo>100){
                                            turbo = 100;
                                        }
                                        int turboInWidth = (int) ((turboOutWidth*98.3/100)*turbo/100);
                                        turboIn.setWidth(turboInWidth);
                                    }
                                }
                            }
                        }
                            if(rivalY>HEIGHT){
                                iterator.remove();
                                ImageView o = (ImageView) carsSources.get(index);
                                group.getChildren().remove(o);
                                carsSources.remove(index);
                            }else{
                                ImageView o = (ImageView) carsSources.get(index);
                                String new_data = rivalX+"-"+rivalY+"-"+isScored+"-"+carType;
                                cars.set(index, new_data);
                                o.setY(rivalY);
                            }
                        }
                    }
                }
                
                // Add Tree on Left Forest
                    if(endedGame==0){
                        int createable = 0;
                        if(leftTreeSources.size()>0){
                            Iterator iterator = leftTree.iterator();
                            while(iterator.hasNext()){
                                String getData = iterator.next().toString();
                                String [] splitedData = getData.split("-");
                                int theTreeX = Integer.parseInt(splitedData[0]);
                                int theTreeY = Integer.parseInt(splitedData[1]);
                                int theTreeWidth = Integer.parseInt(splitedData[2]);
                                if(theTreeY<SPACE_BETWEEN_TREES){
                                    createable+=1;
                                    break;
                                }
                                
                            }
                        }
                        if(createable==0){
                            int coincidence = (int)(Math.random()*100);
                            if(coincidence<3){
                                try{
                                    String last_treeSrc; 
                                    int tree_type = (int) (Math.random()*4);
                                    if(tree_type==0){
                                        last_treeSrc = treeSrc;
                                    }else if(tree_type==1){
                                        last_treeSrc = treeSrc2;
                                    }else if(tree_type==2){
                                        last_treeSrc = treeSrc3;
                                    }else if(tree_type==3){
                                        last_treeSrc = treeSrc4;
                                    }else{
                                        last_treeSrc = treeSrc;
                                    }
                                    Image theTreeImg = new Image(new FileInputStream(last_treeSrc));
                                    ImageView theTree = new ImageView(theTreeImg);
                                    int treeWidth = TREE_MIN_RADIUS + (int)(Math.random()*(TREE_MAX_RADIUS-TREE_MIN_RADIUS));
                                    int treeFromLeft = treeWidth + (int)(Math.random()*(WIDTH*PROPORTION_OF_FOREST/100*65/100)-treeWidth);
                                    if(treeFromLeft<0){
                                        treeFromLeft = 0;
                                    }
                                    int treeFromTop = 0;
                                    String data = treeFromLeft+"-"+treeFromTop+"-"+treeWidth;
                                    theTree.setFitWidth(treeWidth);
                                    theTree.setPreserveRatio(true);
                                    theTree.setX(treeFromLeft);
                                    theTree.setY(treeFromTop);
                                    theTree.setOpacity(TREE_OPACITY);
                                    group.getChildren().add(theTree);
                                    leftTree.add(data);
                                    leftTreeSources.add(theTree);
                                }catch(Exception e){
                                }
                            }
                        }
                    }
                    
                    
                // Add Tree on Right Forest
                    if(endedGame==0){
                        int createable = 0;
                        if(rightTreeSources.size()>0){
                            Iterator iterator = rightTree.iterator();
                            while(iterator.hasNext()){
                                String getData = iterator.next().toString();
                                String [] splitedData = getData.split("-");
                                int theTreeX = Integer.parseInt(splitedData[0]);
                                int theTreeY = Integer.parseInt(splitedData[1]);
                                int theTreeWidth = Integer.parseInt(splitedData[2]);
                                if(theTreeY<SPACE_BETWEEN_TREES){
                                    createable+=1;
                                    break;
                                }
                                
                            }
                        }
                        if(createable==0){
                            int coincidence = (int)(Math.random()*100);
                                if(coincidence<3){
                                try{
                                    String last_treeSrc; 
                                    int tree_type = (int) (Math.random()*4);
                                    if(tree_type==0){
                                        last_treeSrc = treeSrc;
                                    }else if(tree_type==1){
                                        last_treeSrc = treeSrc2;
                                    }else if(tree_type==2){
                                        last_treeSrc = treeSrc3;
                                    }else if(tree_type==3){
                                        last_treeSrc = treeSrc4;
                                    }else{
                                        last_treeSrc = treeSrc;
                                    }
                                    Image theTreeImg = new Image(new FileInputStream(last_treeSrc));
                                    ImageView theTree = new ImageView(theTreeImg);
                                    int treeWidth = TREE_MIN_RADIUS + (int)(Math.random()*(TREE_MAX_RADIUS-TREE_MIN_RADIUS));
                                    int treeFromLeft = treeWidth + (int)(WIDTH*(PROPORTION_OF_FOREST+PROPORTION_OF_ROAD)/100)+ (int)(Math.random()*(WIDTH*PROPORTION_OF_FOREST/100*65/100)-treeWidth);
                                    int treeFromTop = 0;
                                    String data = treeFromLeft+"-"+treeFromTop+"-"+treeWidth;
                                    theTree.setFitWidth(treeWidth);
                                    theTree.setPreserveRatio(true);
                                    theTree.setX(treeFromLeft);
                                    theTree.setY(treeFromTop);
                                    theTree.setOpacity(TREE_OPACITY);
                                    group.getChildren().add(theTree);
                                    rightTree.add(data);
                                    rightTreeSources.add(theTree);
                                }catch(Exception e){
                                }
                            }
                        }
                    }
                    
                    // Animation of Trees on Left Forest
                    if(endedGame==0 && leftTreeSources.size()>0){
                        Iterator iterator = leftTree.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int treeX = Integer.parseInt(splitedData[0]);
                            int treeY = Integer.parseInt(splitedData[1]);
                            int treeWidth = Integer.parseInt(splitedData[2]);
                            int index = leftTree.indexOf(getData);
                            treeY+=gameSpeed;
                            String new_data = treeX+"-"+treeY+"-"+treeWidth;
                            if(treeY>HEIGHT){
                                    iterator.remove();
                                    ImageView o = (ImageView)leftTreeSources.get(index);
                                    leftTreeSources.remove(index);
                                    group.getChildren().remove(o);
                                }else{
                                    ImageView o = (ImageView)leftTreeSources.get(index);
                                    o.setY(treeY);
                                    leftTree.set(index, new_data);
                                }
                         }
                    }
                        
                    // Animation of Trees on Right Forest
                    if(endedGame==0 && rightTreeSources.size()>0){
                        Iterator iterator = rightTree.iterator();
                        while(iterator.hasNext()){
                            String getData = iterator.next().toString();
                            String [] splitedData = getData.split("-");
                            int treeX = Integer.parseInt(splitedData[0]);
                            int treeY = Integer.parseInt(splitedData[1]);
                            int treeWidth = Integer.parseInt(splitedData[2]);
                            int index = rightTree.indexOf(getData);
                            treeY+=gameSpeed;
                            String new_data = treeX+"-"+treeY+"-"+treeWidth;
                            if(treeY>HEIGHT){
                                    iterator.remove();
                                    ImageView o = (ImageView)rightTreeSources.get(index);
                                    rightTreeSources.remove(index);
                                    group.getChildren().remove(o);
                                }else{
                                    ImageView o = (ImageView)rightTreeSources.get(index);
                                    o.setY(treeY);
                                    rightTree.set(index, new_data);
                        }
                    }
                    }
                
                // Car Selector settings
                if(carType==0){
                    previous.setVisible(false);
                    preview.setVisible(true);
                    preview2.setVisible(false);
                    preview3.setVisible(false);
                    infoLabel.setText("More Resistance");
                    int infoFromLeft = carSelectorLabelFromLeft - 31;
                    info.setX(infoFromLeft);
                    infoLabel.setTranslateX(infoFromLeft+17.5);
                }else if(carType==1){
                    next.setVisible(true);
                    previous.setVisible(true);
                    preview.setVisible(false);
                    preview2.setVisible(true);
                    preview3.setVisible(false);
                    infoLabel.setText("More Drift");
                    int infoFromLeft = carSelectorLabelFromLeft - 11;
                    info.setX(infoFromLeft);
                    infoLabel.setTranslateX(infoFromLeft+17.5);
                }else if(carType==2){
                    next.setVisible(false);
                    preview.setVisible(false);
                    preview2.setVisible(false);
                    preview3.setVisible(true);
                    infoLabel.setText("More Speed");
                    int infoFromLeft = carSelectorLabelFromLeft - 16;
                    info.setX(infoFromLeft);
                    infoLabel.setTranslateX(infoFromLeft+17.5);
                }
                
                next.setOnMouseClicked((MouseEvent e)->{
                    carType++;
                });
                previous.setOnMouseClicked((MouseEvent e)->{
                    carType--;
                });
                
                next.setOnMouseMoved((MouseEvent e)->{
                    next.setOpacity(0.8);
                    next.setCursor(Cursor.HAND);
                });
                next.setOnMouseExited((MouseEvent e)->{
                    next.setOpacity(0.5);
                });
                previous.setOnMouseMoved((MouseEvent e)->{
                    previous.setOpacity(0.8);
                    previous.setCursor(Cursor.HAND);
                });
                previous.setOnMouseExited((MouseEvent e)->{
                    previous.setOpacity(0.5);
                });
                
                // Car Specializations
                if(carType==0){
                    speed_rate = 1;
                    resistent_rate = 1.5;
                    movement_rate = 1;
                }else if(carType==1){
                    speed_rate = 1;
                    resistent_rate = 1;
                    movement_rate = 1.2;
                }else if(carType==2){
                    speed_rate = 1.2;
                    resistent_rate = 1;
                    movement_rate = 1;
                }
                
                // Car Z-index Settings
                if(endedGame==0){
                    // DELETE ALL AND SKETCH ALL AGAIN
                    Iterator iterator = carsSources.iterator();
                    while(iterator.hasNext()){
                        ImageView o = (ImageView) iterator.next();
                        group.getChildren().remove(o);
                        group.getChildren().add(o);
                    }
                }
                
                // Update SpeedMeter
                double indicator = gameSpeed * 20;
                double indicatorMax = ((4+level_live)*speed_rate) * 20;
                double proportion = (indicator/indicatorMax)*100;
                double angle = (282 * proportion/100) - 141;
                speedMeterNeedle.setRotate(angle);
                
                // Update Experience
                int beforeExperience;
                if(level_live==1){
                    beforeExperience = 0;
                }else{
                    beforeExperience = ((level_live-1)*(level_live-1)) + 5;
                }
                int needExperience = (level_live*level_live+5 - beforeExperience);
                int netExperience = experience - beforeExperience;
                double calculated = ((double)netExperience/(double)needExperience)*100;
                String changableString;
                if(calculated>=80){
                    changableString = "[ % % % % _ ]";
                }else if(calculated>=60){
                    changableString = "[ % % % _ _ ]";
                }else if(calculated>=40){
                    changableString = "[ % % % _ _ ]";
                }else if(calculated>=20){
                    changableString = "[ % _ _ _ _ ]";
                }else{
                    changableString = "[ _ _ _ _ _ ]";
                }
                theExperienceIn.setWidth(calculated*99/100);
                
            }
        };
        
        animator.start();
        
    }
    // Crash Control Function
    public static boolean hitControl(int x1,int y1,int x2,int y2){
        if(x1>x2 && x1<x2+CAR_WIDTH){
            if(y1>y2 && y1<y2+CAR_HEIGHT){
                return true;
            }else if(y1+CAR_HEIGHT>y2 && y1+CAR_HEIGHT<y2+CAR_HEIGHT){
                return true;
            }
        }else if(x1+CAR_WIDTH>x2 && x1+CAR_WIDTH<x2+CAR_WIDTH){
            if(y1>y2 && y1<y2+CAR_HEIGHT){
                return true;
            }else if(y1+CAR_HEIGHT>y2 && y1+CAR_HEIGHT<y2+CAR_HEIGHT){
                return true;
            }
        }else if(y1>y2 && y1<y2+CAR_HEIGHT){
            if(x1>x2 && x1<x2+CAR_WIDTH){
                return true;
            }else if(x1+CAR_WIDTH>x2 && x1+CAR_WIDTH<x2+CAR_WIDTH){
                return true;
            }
        }else if(y1+CAR_HEIGHT>y2 && y1+CAR_HEIGHT<y2+CAR_HEIGHT){
            if(x1>x2 && x1<x2+CAR_WIDTH){
                return true;
            }else if(x1+CAR_WIDTH>x2 && x1+CAR_WIDTH<x2+CAR_WIDTH){
                return true;
            }
        }
        return false;
    }
    // Crash Risk Function
    public static boolean riskControl(int x1,int y1,int x2,int y2,int r){
        int minLR = x1 - r;
        int maxLR = x1 + r + CAR_WIDTH;
        int minUD = y1 - r;
        int maxUD = y1 + r + CAR_HEIGHT;
        int x2_rightSide = x2 + CAR_WIDTH;
        int y2_botSide = y2 + CAR_HEIGHT;
        int x2_midSide = x2 + CAR_WIDTH/2;
        int y2_midSide = y2 + CAR_HEIGHT/2;
        if(x2>=minLR && x2<=maxLR && y2>=minUD && y2<=maxUD){
            return true;
        }else if(x2_rightSide>=minLR && x2_rightSide<=maxLR && y2>=minUD && y2<=maxUD){
            return true;
        }else if(x2_rightSide>=minLR && x2_rightSide<=maxLR && y2_botSide>=minUD && y2_botSide<=maxUD){
            return true;
        }else if(x2>=minLR && x2<=maxLR && y2_botSide>=minUD && y2_botSide<=maxUD){
            return true;
        }else if(x2>=minLR && x2<=maxLR && y2_midSide>=minUD && y2_midSide<=maxUD){
            return true;
        }else if(x2_rightSide>=minLR && x2_rightSide<=maxLR && y2_midSide>=minUD && y2_midSide<=maxUD){
            return true;
        }else if(x2_midSide>=minLR && x2_midSide<=maxLR && y2>=minUD && y2<=maxUD){
            return true;
        }else if(x2_midSide>=minLR && x2_midSide<=maxLR && y2_botSide>=minUD && y2_botSide<=maxUD){
            return true;
        }else{
            return false;
        }
    }
}