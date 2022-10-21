const readPost = async (req, res) => {
    res.json({
        posts: {
            title: "FoodPay",
            description: "A payment foodcourt application"
        }
    });   
}

module.exports = readPost;