const router = require("express-promise-router")();
//const router = require("express").Router();
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const User = require("../models/User");
const passport = require("passport");
const passportFacebook = require("passport-facebook");
const forgot = require('password-reset')({
    uri : 'http://localhost:3000/password_reset',
    from : 'password-robot@localhost',
    host : 'localhost', port : 25,
});

const {
    index,
    newUser,
    getUser,
    replaceUser,
    deleteUser,
    newUser2,
    loginUser,
    logout,
    facebookOAuth,
    loginUserGoogle
} = require("../controllers/user");

router.get("/", index)
router.post("/", newUser)
//router.post("/", newUser2)
router.get("/:userId", getUser);
router.put("/:userId", replaceUser);
router.delete("/:userId", deleteUser);

router.post("/signup", newUser2);
router.post("/login", loginUser);
router.post("/logout", logout);
router.post("/loginGoogle2", loginUserGoogle);


/* Avance con Passport*/
router.get("/signinn", (req, res) => {
    //res.render("users/signin");
    console.log(result);
    res.status(201).json({
        message: "User created"
    });
})


router.post("/signinn", passport.authenticate("local", {
    successRedirect: "/users",
    //failureRedirect: "/users/login"
    //failureFlash: true
    
    
    }
));

router.post("/loginFacebook", passport.authenticate("facebook-token", facebookOAuth, {
    successRedirect: "/users" 
    //failureRedirect: "/users"  
    /*function (req, res) {
        // do something with req.user
        res.send(req.user? 200 : 401);
        console.log("Hi")
      }*/
    }
));

router.post("/loginGoogle", passport.authenticate("google", {
    successRedirect: "/users"
}))

router.post("/loginTwitter", passport.authenticate("twitter", {
    successRedirect: "/users",
    failureRedirect: "/users/login"
}))

router.post("/twitter/login", passport.authenticate("twitter"));

router.get("/twitter/return", passport.authenticate("twitter", {
    failureRedirect: "/"
}), function(req, res){
    successRedirect: "/profile"
})

router.post('/forgot', function (req, res) {
    var email = req.body.email;
    var reset = forgot(email, function (err) {
        if (err) res.end('Error sending message: ' + err)
        else res.end('Check your inbox for a password reset message.')
    });
    
    reset.on('request', function (req_, res_) {
        req_.session.reset = { email : email, id : reset.id };
        fs.createReadStream(__dirname + '/forgot.html').pipe(res_);
    });
});

router.post('/reset', function (req, res) {
    if (!req.session.reset) return res.end('reset token not set');
    
    var password = req.body.password;
    var confirm = req.body.confirm;
    if (password !== confirm) return res.end('passwords do not match');
    
    // update the user db here
    
    forgot.expire(req.session.reset.id);
    delete req.session.reset;
    res.end('password reset');
});





module.exports = router;
