const passport = require("passport");
const LocalStrategy = require("passport-local").Strategy;
const FacebookTokenStrategy = require("passport-facebook-token");
const GooglePlusTokenStrategy = require("passport-google-plus-token");
const TwitterStrategy = require('passport-twitter').Strategy;

const User = require("../models/User");

//Autentificación Local
passport.use(new LocalStrategy({
    usernameField: "email"
}, async (email, password, done) => {
    const user = await User.findOne({ email: email });
    if (!user) {
        return done(null, false, { message: "Usuario no encontrado" });
    } else {
        const match = await user.matchPassword(password);
        if (match) {
            return done(null, user);
        } else {
            return done(null, false, { message: "Contraseña incorrecta" });
        }
    }
}));


passport.serializeUser(function(user, done) {
    done(null, user._id);
});

passport.deserializeUser(function(id, done) {
  User.findById(id, function(err, user) {
    done(err, user);
  });
});

//Autentificación por Facebook
passport.use(new FacebookTokenStrategy({
    clientID: "2100491900262029",
    clientSecret: "35bf1b934c1ed045f7e6b17264782c88",
}, async (accessToken, refreshToken, profile, done)=>{
    try {
        console.log('profile', profile);
        console.log('refreshToken', refreshToken);
        console.log('accessToken', accessToken);

        const existingUser = await User.findOne({"id": profile.id});
        if(existingUser){
            console.log("El usuario ya existe en la base de datos");
            return done(null, existingUser);
        }

        console.log("El usuario no existe, se creará en la base de datos");
        const newUser = new User({
            id: profile.id,
            email: profile.emails[0].value,
            gender: profile.gender,
            name: profile.name.givenName
        });
        await newUser.save();
        done(null, newUser);
    } catch(error){
        done(error, false, error.message);
    }
}));

//Autentificación por Google+
passport.use("google", new GooglePlusTokenStrategy({
    clientID: "503524599322-aihkdacm33o7k02po1oafi1b9j564jgq.apps.googleusercontent.com",
    clientSecret: "kZIv0igrnASoHbOhmMHzCMQS"
}, async (accessToken, refreshToken, profile, done)=>{
    try {
        console.log("accessToken", accessToken);
        console.log("refreshToken", refreshToken);
        console.log("profile", profile);

        // Checa si el usuario existe en la DB
        const existingUser = await User.findOne({"id":profile.id});
        if(existingUser){
            console.log("El usuario ya existe en la base de datos");
            return done(null, existingUser);
        }
        console.log("El usuario no existe, se creará en la base de datos");
        const newUser = new User({
            id: profile.id,
            email: profile.emails[0].value
        });
        await newUser.save();
        done(null, newUser);
    } catch (error) {
        done(error, false, error.message);
    }
}));

//Autentificación por Twitter
passport.use(new TwitterStrategy({

  consumerKey:    "fIooj6sd4HjDE1I7KSUqnLRXD",

  consumerSecret: "cgGFyFgEDGTWdGuOolQvXSI9dJf9i6P6bqOUMj50ONBJBxHOrg",

  callbackURL:    "http://localhost:3000/twitter/return"
},
  function(token, tokenSecret, profile, callback) {

    return callbackURL(null, profile);

  }));
passport.serializeUser(function(user, callback){
    callback(null, user);
})
passport.deserializeUser(function(obj, callback){
    callback(null, obj);
})