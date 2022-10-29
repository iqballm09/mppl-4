const User = require("../../models/User");
const Card = require("../../models/Card");
const bcrypt = require("bcryptjs");

const updateUser = async (req, res) => {
    let hashNewPassword, updatedPassword;
    // Get payload
    const email = req.user.email;
    const user = await User.findOne({
        where: { email: email }
    });
    if (!user) return res.status(404).send("User not found");
    // Change password. StatusCP = Status of Change Password
    if (req.body.statusCP === true) {
        if (req.body.oldPassword) {
            // Checking if old password is correct
            const validOldPassword = await bcrypt.compare(req.body.oldPassword, user.password);
            if (!validOldPassword) return res.status(400).send("Invalid password!");
            else {
                const salt = await bcrypt.genSalt(15);
                hashNewPassword = await bcrypt.hash(req.body.newPassword, salt);
            }
        } else {
            return res.status(500).send("Old password is empty");
        }
    }

    // Updated password
    updatedPassword = hashNewPassword;
    if (!updatedPassword) {
        updatedPassword = user.password;
    }

    // Update another fields
    user.set({
        name: req.body.name,
        password: updatedPassword,
        phoneNumber: req.body.phoneNumber,
        photo: req.body.photo
    });

    await user.save();

    // Get updated user
    const updatedUser = await User.findOne({
        where: { email: email }
    });
    return res.json({ updatedUser });
}

module.exports = updateUser;