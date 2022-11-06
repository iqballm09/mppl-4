const TopUp = require("../../models/TopUp");
const bcrypt = require("bcryptjs");
const Card = require("../../models/Card");
const dotenv = require("dotenv");

dotenv.config();

const createTopup = async (req, res) => {
    // Get payload
    const userID = req.user.id;
    // Read card
    const card = await Card.findOne({ 
        where: { UserID: userID }
    });
    if (!card) return res.status(404).send(`Card with UserID: ${userID} is not found`);   
}