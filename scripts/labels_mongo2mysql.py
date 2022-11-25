import aiohttp
import asyncio

mongo_url = 'http://www.fadinglight.cn:8088/class'
mysql_url = 'http://localhost:8080/api/v1/label/'


async def getMongoLabels():
    async with aiohttp.ClientSession() as session:
        async with session.get(mongo_url) as response:
            data = await response.json()
    return data


async def postLabelsToMysql(label):
    async with aiohttp.ClientSession() as session:
        async with session.post(mysql_url, json=label) as response:
            data = await response.json()
    return data


def newLabel(name, type=0, relativeId=None):
    return dict(
        name=name,
        type=type,
        relativeId=relativeId,
    )


async def main():
    data = await getMongoLabels()
    labels = data['data']
    consumeLabels = labels['consume']
    incomeLabels = labels['income']

    print(consumeLabels)

    for i in incomeLabels:
        label = newLabel(i, type=2)
        # print(label)
        await postLabelsToMysql(label)

    for i in consumeLabels:
        subLabels = consumeLabels[i]
        label = newLabel(i, type=0)
        resp = await postLabelsToMysql(label)
        id = resp['data']
        print(id)
        for j in subLabels:
            sub_label = newLabel(j, type=1, relativeId=id)
            await postLabelsToMysql(sub_label)


asyncio.run(main())
