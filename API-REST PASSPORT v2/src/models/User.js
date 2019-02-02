const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const bcrypt = require("bcryptjs");


const userSchema = new Schema({
    id: {type: String, required: false},
    name: { type: String, required: true },
    last_name: { type: String, required: true },
    user_name: { type: String, required: true },
    password: { type: String, required: true},
    birthdate: { type: Date, required: true},
    email: {
        type: String,
        required: true,
        unique: false,
        match: /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/
    },
    gender: { type: String, required: true}
});
userSchema.methods.encryptPassword = async (password)=>{
    const salt = await bcrypt.genSalt(10);
    const hash = bcrypt.hash(password, salt);
    return hash;
};

userSchema.methods.matchPassword = async function (password){
    return await bcrypt.compare(password, this.password)
}

module.exports = mongoose.model("User", userSchema);
