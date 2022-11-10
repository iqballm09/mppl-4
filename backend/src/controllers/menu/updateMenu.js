const Menu = require("../../models/Menu");
const Merchant = require("../../models/Merchant");
const dotenv = require("dotenv");

dotenv.config();

const updateMenu = async (req, res) => {
    // Get payload
    const merchantID = req.merchant.id;
    // Get merchant by id
    const merchant = await Merchant.findOne({
        where: { id: merchantID }
    });
    // Update menu
    const menu = await Menu.findOne({
        where: {
            id: req.body.id,
            MerchantID: merchant.id
        }
    });
    if(!menu) return res.status(404).send(`Menu with id: ${ req.body.id } and MerchantID: ${ merchant.id } is not found`);
    menu.set({
        name: req.body.name,
        type: req.body.type,
        price: req.body.price,
        photo: req.body.photo
    });
    await menu.save();
    // Get updated menu
    const updatedMenu = await Menu.findOne({
        where: { id: merchantID }
    });
    return res.status(201).json({ updatedMenu });
}

module.exports = updateMenu;