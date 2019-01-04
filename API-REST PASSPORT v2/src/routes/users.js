const router = require("express-promise-router")();
//const router = require("express").Router();
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const User = require("../models/User");
const passport = require("passport");
const passportFacebook = require("passport-facebook");

const {
    index,
    newUser,
    getUser,
    replaceUser,
    deleteUser,
    newUser2,
    loginUser,
    logout,
    facebookOAuth
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




module.exports = router;
