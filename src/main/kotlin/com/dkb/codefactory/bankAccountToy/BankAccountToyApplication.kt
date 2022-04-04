package com.dkb.codefactory.bankAccountToy

import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BankAccountToyApplication
//{

//	@Bean
//	fun commandLineRunner(accountRepository: AccountRepository): CommandLineRunner =
//		CommandLineRunner {
//			println("************************************")
//			print(accountRepository.getBalance(1))
//		}
//}

//fun <T: Comparable<T>> getSmaller(param1: T, param2: T) : T {
//	val res = param1.compareTo(param2)
//	return if (res < 0) param1 else param2
//}
//
//data class Cubic(val x: Int, val y: Int, val z: Int) {
//
//	operator fun plus(other: Cubic): Cubic {
//		return Cubic(x + other.x, y + other.y, z + other.z)
//	}
//
//	operator fun minus(other: Cubic): Cubic {
//		return Cubic(x - other.x, y - other.y, z - other.z)
//	}
//
//}

fun main(args: Array<String>) {
	runApplication<BankAccountToyApplication>(*args)

	//return

//	val pt1 = Cubic(100, 20, 300)
//	val pt2 = Cubic(50, 70, 40)
//
//	println("pt1 = $pt1")
//	println("pt2 = $pt2")
//	println("pt1 + pt2 = ${pt1 + pt2}")
//	println("pt1 - pt2 = ${pt1 - pt2}")
//
//
//	return
//
//	val minInt: Int = getSmaller(42, 99)
//	val minDouble: Double = getSmaller(872.6, 55.0)
//	val minString: String = getSmaller("seren", "ceren")
//
//	println(minInt)
//	println(minDouble)
//	println(minString)
//
//	return
//	for (num in 1..185) {
//		when {
//			isFizz(num) && isBuzz(num) -> println("fizz buzz")
//			isFizz(num) -> println("fizz")
//			isBuzz(num) -> println("buzz")
//			else -> println(num)
//		}
//	}

}

//fun isFizz(num: Int) = num % 3 == 0
//fun isBuzz(num: Int) = num % 5 == 0



