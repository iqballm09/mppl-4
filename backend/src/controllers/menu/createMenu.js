const Menu = require("../../models/Menu");
const Merchant = require("../../models/Merchant");
const dotenv = require("dotenv");

dotenv.config();

const createMenu = async (req, res) => {
    // Get Payload
    const merchantID = req.merchant.id;
    // Get merchant by id
    const merchant = await Merchant.findOne({
        where: { id: merchantID }
    });
    // createMenu
    try {
        const menu = await Menu.create({
            MerchantID: merchant.id,
            name: req.body.name,
            price: req.body.price,
            type: req.body.type
        });
        await menu.save();
        return res.status(201).json({ menu });
    } catch(error) {
        return res.status(500).send(error.message);
    }
}

module.exports = createMenu;