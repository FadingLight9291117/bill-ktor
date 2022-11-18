import aiohttp
import asyncio

mongo_url = 'http://www.fadinglight.cn:8088/list'
mysql_url = 'http://localhost:8080/api/v1/bill/'

async def getMongoBills():
    async with aiohttp.ClientSession() as session:
        async with session.get(mongo_url) as response:
            data = await response.json()
    return data

async def postBillsToMysql(bills):
    async with aiohttp.ClientSession() as session:
        async with session.post(mysql_url, json=bills) as response:
            data = await response.json()
    return data

def dealOneBill(bill):
    bill.setdefault('type', 0)
    return dict(
        money=bill['money'],
        cls=bill['cls'],
        label=bill['label'],
        date=bill['date'],
        options=bill['options'],
        type="consume" if bill['type'] == 0 else "income",
    )

async def main():
    data = await getMongoBills()
    bills = data['data']
    bills = list(map(dealOneBill, bills))
    respData = await postBillsToMysql(bills)
    print(respData)

asyncio.run(main())