//   Copyright 2014 Commonwealth Bank of Australia
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package au.com.cba.omnia.humbug

import scala.util.Success

import com.twitter.bijection.scrooge.CompactScalaCodec

import com.cba.omnia.test.Spec

import au.com.cba.omnia.humbug.test._
import au.com.cba.omnia.humbug.test.Arbitraries._

class HumbugSpec extends Spec { def is = s2"""

Humbug
======

  Can generate a valid Thrift struct from a thrift file                            $roundtrip
  Can generate a valid Thrift struct with list fields                              $list
  Can generate a valid Thrift struct with map fields                               $map
  Can generate a valid Thrift struct with nested complex fields                    $nested
  Can generate a valid Thrift struct from a thrift file with more than 255 members $large
  Product element returns the correct member                                       $productElement

"""

  implicit val codec       = CompactScalaCodec[Types](Types)
  implicit val codecList   = CompactScalaCodec[Listish](Listish)
  implicit val codecMap    = CompactScalaCodec[Mapish](Mapish)
  implicit val codecNested = CompactScalaCodec[Nested](Nested)
  implicit val codecLarge  = CompactScalaCodec[Large](Large)

  def roundtrip = prop { (c: Types) =>
    codec.invert(codec(c)) must_== Success(c)
  }

  def list = prop { (c: Listish) =>
    codecList.invert(codecList(c)) must_== Success(c)
  }

  def map = prop { (c: Mapish) =>
    codecMap.invert(codecMap(c)) must_== Success(c)
  }

  def nested = prop { (c: Nested) =>
    codecNested.invert(codecNested(c)) must_== Success(c)
  }

  def productElement = prop { (c: Types) =>
    c.productArity must_== 9

    c.productElement(0) must_== c.stringField
    c.productElement(1) must_== c.booleanField
    c.productElement(2) must_== c.shortField
    c.productElement(3) must_== c.intField
    c.productElement(4) must_== c.longField
    c.productElement(5) must_== c.doubleField
    c.productElement(6) must_== c.byteField
    c.productElement(7) must_== c.optStringField
    c.productElement(8) must_== c.optDoubleField
  }

  def large = {
    val x = createLarge
    codecLarge.invert(codecLarge(x)) must_== Success(x)
  }

  def createLarge: Large = {
    val x = new Large()
    x._1 = "1"
    x._2 = true
    x._3 = 3
    x._4 = 4
    x._5 = 5L
    x._6 = 6.0
    x._7 = 7
    x._8 = None
    x._9 = Some("9")
    x._10 = "10"
    x._11 = "11"
    x._12 = "12"
    x._13 = "13"
    x._14 = "14"
    x._15 = "15"
    x._16 = "16"
    x._17 = "17"
    x._18 = "18"
    x._19 = "19"
    x._20 = "20"
    x._21 = "21"
    x._22 = "22"
    x._23 = "23"
    x._24 = "24"
    x._25 = "25"
    x._26 = "26"
    x._27 = "27"
    x._28 = "28"
    x._29 = "29"
    x._30 = "30"
    x._31 = "31"
    x._32 = "32"
    x._33 = "33"
    x._34 = "34"
    x._35 = "35"
    x._36 = "36"
    x._37 = "37"
    x._38 = "38"
    x._39 = "39"
    x._40 = "40"
    x._41 = "41"
    x._42 = "42"
    x._43 = "43"
    x._44 = "44"
    x._45 = "45"
    x._46 = "46"
    x._47 = "47"
    x._48 = "48"
    x._49 = "49"
    x._50 = "50"
    x._51 = "51"
    x._52 = "52"
    x._53 = "53"
    x._54 = "54"
    x._55 = "55"
    x._56 = "56"
    x._57 = "57"
    x._58 = "58"
    x._59 = "59"
    x._60 = "60"
    x._61 = "61"
    x._62 = "62"
    x._63 = "63"
    x._64 = "64"
    x._65 = "65"
    x._66 = "66"
    x._67 = "67"
    x._68 = "68"
    x._69 = "69"
    x._70 = "70"
    x._71 = "71"
    x._72 = "72"
    x._73 = "73"
    x._74 = "74"
    x._75 = "75"
    x._76 = "76"
    x._77 = "77"
    x._78 = "78"
    x._79 = "79"
    x._80 = "80"
    x._81 = "81"
    x._82 = "82"
    x._83 = "83"
    x._84 = "84"
    x._85 = "85"
    x._86 = "86"
    x._87 = "87"
    x._88 = "88"
    x._89 = "89"
    x._90 = "90"
    x._91 = "91"
    x._92 = "92"
    x._93 = "93"
    x._94 = "94"
    x._95 = "95"
    x._96 = "96"
    x._97 = "97"
    x._98 = "98"
    x._99 = "99"
    x._100 = "100"
    x._101 = "101"
    x._102 = "102"
    x._103 = "103"
    x._104 = "104"
    x._105 = "105"
    x._106 = "106"
    x._107 = "107"
    x._108 = "108"
    x._109 = "109"
    x._110 = "110"
    x._111 = "111"
    x._112 = "112"
    x._113 = "113"
    x._114 = "114"
    x._115 = "115"
    x._116 = "116"
    x._117 = "117"
    x._118 = "118"
    x._119 = "119"
    x._120 = "120"
    x._121 = "121"
    x._122 = "122"
    x._123 = "123"
    x._124 = "124"
    x._125 = "125"
    x._126 = "126"
    x._127 = "127"
    x._128 = "128"
    x._129 = "129"
    x._130 = "130"
    x._131 = "131"
    x._132 = "132"
    x._133 = "133"
    x._134 = "134"
    x._135 = "135"
    x._136 = "136"
    x._137 = "137"
    x._138 = "138"
    x._139 = "139"
    x._140 = "140"
    x._141 = "141"
    x._142 = "142"
    x._143 = "143"
    x._144 = "144"
    x._145 = "145"
    x._146 = "146"
    x._147 = "147"
    x._148 = "148"
    x._149 = "149"
    x._150 = "150"
    x._151 = "151"
    x._152 = "152"
    x._153 = "153"
    x._154 = "154"
    x._155 = "155"
    x._156 = "156"
    x._157 = "157"
    x._158 = "158"
    x._159 = "159"
    x._160 = "160"
    x._161 = "161"
    x._162 = "162"
    x._163 = "163"
    x._164 = "164"
    x._165 = "165"
    x._166 = "166"
    x._167 = "167"
    x._168 = "168"
    x._169 = "169"
    x._170 = "170"
    x._171 = "171"
    x._172 = "172"
    x._173 = "173"
    x._174 = "174"
    x._175 = "175"
    x._176 = "176"
    x._177 = "177"
    x._178 = "178"
    x._179 = "179"
    x._180 = "180"
    x._181 = "181"
    x._182 = "182"
    x._183 = "183"
    x._184 = "184"
    x._185 = "185"
    x._186 = "186"
    x._187 = "187"
    x._188 = "188"
    x._189 = "189"
    x._190 = "190"
    x._191 = "191"
    x._192 = "192"
    x._193 = "193"
    x._194 = "194"
    x._195 = "195"
    x._196 = "196"
    x._197 = "197"
    x._198 = "198"
    x._199 = "199"
    x._200 = "200"
    x._201 = "201"
    x._202 = "202"
    x._203 = "203"
    x._204 = "204"
    x._205 = "205"
    x._206 = "206"
    x._207 = "207"
    x._208 = "208"
    x._209 = "209"
    x._210 = "210"
    x._211 = "211"
    x._212 = "212"
    x._213 = "213"
    x._214 = "214"
    x._215 = "215"
    x._216 = "216"
    x._217 = "217"
    x._218 = "218"
    x._219 = "219"
    x._220 = "220"
    x._221 = "221"
    x._222 = "222"
    x._223 = "223"
    x._224 = "224"
    x._225 = "225"
    x._226 = "226"
    x._227 = "227"
    x._228 = "228"
    x._229 = "229"
    x._230 = "230"
    x._231 = "231"
    x._232 = "232"
    x._233 = "233"
    x._234 = "234"
    x._235 = "235"
    x._236 = "236"
    x._237 = "237"
    x._238 = "238"
    x._239 = "239"
    x._240 = "240"
    x._241 = "241"
    x._242 = "242"
    x._243 = "243"
    x._244 = "244"
    x._245 = "245"
    x._246 = "246"
    x._247 = "247"
    x._248 = "248"
    x._249 = "249"
    x._250 = "250"
    x._251 = "251"
    x._252 = "252"
    x._253 = "253"
    x._254 = "254"
    x._255 = "255"
    x._256 = "256"
    x._257 = "257"
    x._258 = "258"
    x._259 = "259"
    x._260 = "260"
    x._261 = "261"
    x._262 = "262"
    x._263 = "263"
    x._264 = "264"
    x._265 = "265"
    x._266 = "266"
    x._267 = "267"
    x._268 = "268"
    x._269 = "269"
    x._270 = "270"
    x._271 = "271"
    x._272 = "272"
    x._273 = "273"
    x._274 = "274"
    x._275 = "275"
    x._276 = "276"
    x._277 = "277"
    x._278 = "278"
    x._279 = "279"
    x._280 = "280"
    x._281 = "281"
    x._282 = "282"
    x._283 = "283"
    x._284 = "284"
    x._285 = "285"
    x._286 = "286"
    x._287 = "287"
    x._288 = "288"
    x._289 = "289"
    x._290 = "290"
    x._291 = "291"
    x._292 = "292"
    x._293 = "293"
    x._294 = "294"
    x._295 = "295"
    x._296 = "296"
    x._297 = "297"
    x._298 = "298"
    x._299 = "299"
    x._300 = "300"
    x._301 = "301"
    x._302 = "302"
    x._303 = "303"
    x._304 = "304"
    x._305 = "305"
    x._306 = "306"
    x._307 = "307"
    x._308 = "308"
    x._309 = "309"
    x._310 = "310"
    x._311 = "311"
    x._312 = "312"
    x._313 = "313"
    x._314 = "314"
    x._315 = "315"
    x._316 = "316"
    x._317 = "317"
    x._318 = "318"
    x._319 = "319"
    x._320 = "320"
    x._321 = "321"
    x._322 = "322"
    x._323 = "323"
    x._324 = "324"
    x._325 = "325"
    x._326 = "326"
    x._327 = "327"
    x._328 = "328"
    x._329 = "329"
    x._330 = "330"
    x._331 = "331"
    x._332 = "332"
    x._333 = "333"
    x._334 = "334"
    x._335 = "335"
    x._336 = "336"
    x._337 = "337"
    x._338 = "338"
    x._339 = "339"
    x._340 = "340"
    x._341 = "341"
    x._342 = "342"
    x._343 = "343"
    x._344 = "344"
    x._345 = "345"
    x._346 = "346"
    x._347 = "347"
    x._348 = "348"
    x._349 = "349"
    x._350 = "350"
    x._351 = "351"
    x._352 = "352"
    x._353 = "353"
    x._354 = "354"
    x._355 = "355"
    x._356 = "356"
    x._357 = "357"
    x._358 = "358"
    x._359 = "359"
    x._360 = "360"
    x._361 = "361"
    x._362 = "362"
    x._363 = "363"
    x._364 = "364"
    x._365 = "365"
    x._366 = "366"
    x._367 = "367"
    x._368 = "368"
    x._369 = "369"
    x._370 = "370"
    x._371 = "371"
    x._372 = "372"
    x._373 = "373"
    x._374 = "374"
    x._375 = "375"
    x._376 = "376"
    x._377 = "377"
    x._378 = "378"
    x._379 = "379"
    x._380 = "380"
    x._381 = "381"
    x._382 = "382"
    x._383 = "383"
    x._384 = "384"
    x._385 = "385"
    x._386 = "386"
    x._387 = "387"
    x._388 = "388"
    x._389 = "389"
    x._390 = "390"
    x._391 = "391"
    x._392 = "392"
    x._393 = "393"
    x._394 = "394"
    x._395 = "395"
    x._396 = "396"
    x._397 = "397"
    x._398 = "398"
    x._399 = "399"
    x._400 = "400"
    x._401 = "401"
    x._402 = "402"
    x._403 = "403"
    x._404 = "404"
    x._405 = "405"
    x._406 = "406"
    x._407 = "407"
    x._408 = "408"
    x._409 = "409"
    x._410 = "410"
    x._411 = "411"
    x._412 = "412"
    x._413 = "413"
    x._414 = "414"
    x._415 = "415"
    x._416 = "416"
    x._417 = "417"
    x._418 = "418"
    x._419 = "419"
    x._420 = "420"
    x._421 = "421"
    x._422 = "422"
    x._423 = "423"
    x._424 = "424"
    x._425 = "425"
    x._426 = "426"
    x._427 = "427"
    x._428 = "428"
    x._429 = "429"
    x._430 = "430"
    x._431 = "431"
    x._432 = "432"
    x._433 = "433"
    x._434 = "434"
    x._435 = "435"
    x._436 = "436"
    x._437 = "437"
    x._438 = "438"
    x._439 = "439"
    x._440 = "440"
    x._441 = "441"
    x._442 = "442"
    x._443 = "443"
    x._444 = "444"
    x._445 = "445"
    x._446 = "446"
    x._447 = "447"
    x._448 = "448"
    x._449 = "449"
    x._450 = "450"
    x._451 = "451"
    x._452 = "452"
    x._453 = "453"
    x._454 = "454"
    x._455 = "455"
    x._456 = "456"
    x._457 = "457"
    x._458 = "458"
    x._459 = "459"
    x._460 = "460"
    x._461 = "461"
    x._462 = "462"
    x._463 = "463"
    x._464 = "464"
    x._465 = "465"
    x._466 = "466"
    x._467 = "467"
    x._468 = "468"
    x._469 = "469"
    x._470 = "470"
    x._471 = "471"
    x._472 = "472"
    x._473 = "473"
    x._474 = "474"
    x._475 = "475"
    x._476 = "476"
    x._477 = "477"
    x._478 = "478"
    x._479 = "479"
    x._480 = "480"
    x._481 = "481"
    x._482 = "482"
    x._483 = "483"
    x._484 = "484"
    x._485 = "485"
    x._486 = "486"
    x._487 = "487"
    x._488 = "488"
    x._489 = "489"
    x._490 = "490"
    x._491 = "491"
    x._492 = "492"
    x._493 = "493"
    x._494 = "494"
    x._495 = "495"
    x._496 = "496"
    x._497 = "497"
    x._498 = "498"
    x._499 = "499"
    x._500 = "500"
    x._501 = "501"
    x._502 = "502"
    x._503 = "503"
    x._504 = "504"
    x._505 = "505"
    x._506 = "506"
    x._507 = "507"
    x._508 = "508"
    x._509 = "509"
    x._510 = "510"
    x._511 = "511"
    x._512 = "512"
    x._513 = "513"
    x._514 = "514"
    x._515 = "515"
    x._516 = "516"
    x._517 = "517"
    x._518 = "518"
    x._519 = "519"
    x._520 = "520"
    x._521 = "521"
    x._522 = "522"
    x._523 = "523"
    x._524 = "524"
    x._525 = "525"
    x._526 = "526"
    x._527 = "527"
    x._528 = "528"
    x._529 = "529"
    x._530 = "530"
    x._531 = "531"
    x._532 = "532"
    x._533 = "533"
    x._534 = "534"
    x._535 = "535"
    x._536 = "536"
    x._537 = "537"
    x._538 = "538"
    x._539 = "539"
    x._540 = "540"
    x._541 = "541"
    x._542 = "542"
    x._543 = "543"
    x._544 = "544"
    x._545 = "545"
    x._546 = "546"
    x._547 = "547"
    x._548 = "548"
    x._549 = "549"
    x._550 = "550"
    x._551 = "551"
    x._552 = "552"
    x._553 = "553"
    x._554 = "554"
    x._555 = "555"
    x._556 = "556"
    x._557 = "557"
    x._558 = "558"
    x._559 = "559"
    x._560 = "560"
    x._561 = "561"
    x._562 = "562"
    x._563 = "563"
    x._564 = "564"
    x._565 = "565"
    x._566 = "566"
    x._567 = "567"
    x._568 = "568"
    x._569 = "569"
    x._570 = "570"
    x._571 = "571"
    x._572 = "572"
    x._573 = "573"
    x._574 = "574"
    x._575 = "575"
    x._576 = "576"
    x._577 = "577"
    x._578 = "578"
    x._579 = "579"
    x._580 = "580"
    x._581 = "581"
    x._582 = "582"
    x._583 = "583"
    x._584 = "584"
    x._585 = "585"
    x._586 = "586"
    x._587 = "587"
    x._588 = "588"
    x._589 = "589"
    x._590 = "590"
    x._591 = "591"
    x._592 = "592"
    x._593 = "593"
    x._594 = "594"
    x._595 = "595"
    x._596 = "596"
    x._597 = "597"
    x._598 = "598"
    x._599 = "599"
    x._600 = "600"
    x._601 = "601"
    x._602 = "602"
    x._603 = "603"
    x._604 = "604"
    x._605 = "605"
    x._606 = "606"
    x._607 = "607"
    x._608 = "608"
    x._609 = "609"
    x._610 = "610"
    x._611 = "611"
    x._612 = "612"
    x._613 = "613"
    x._614 = "614"
    x._615 = "615"
    x._616 = "616"
    x._617 = "617"
    x._618 = "618"
    x._619 = "619"
    x._620 = "620"
    x._621 = "621"
    x._622 = "622"
    x._623 = "623"
    x._624 = "624"
    x._625 = "625"
    x._626 = "626"
    x._627 = "627"
    x._628 = "628"
    x._629 = "629"
    x._630 = "630"
    x._631 = "631"
    x._632 = "632"
    x._633 = "633"
    x._634 = "634"
    x._635 = "635"
    x._636 = "636"
    x._637 = "637"
    x._638 = "638"
    x._639 = "639"
    x._640 = "640"
    x._641 = "641"
    x._642 = "642"
    x._643 = "643"
    x._644 = "644"
    x._645 = "645"
    x._646 = "646"
    x._647 = "647"
    x._648 = "648"
    x._649 = "649"
    x._650 = "650"
    x._651 = "651"
    x._652 = "652"
    x._653 = "653"
    x._654 = "654"
    x._655 = "655"
    x._656 = "656"
    x._657 = "657"
    x._658 = "658"
    x._659 = "659"
    x._660 = "660"
    x._661 = "661"
    x._662 = "662"
    x._663 = "663"
    x._664 = "664"
    x._665 = "665"
    x._666 = "666"
    x._667 = "667"
    x._668 = "668"
    x._669 = "669"
    x._670 = "670"
    x._671 = "671"
    x._672 = "672"
    x._673 = "673"
    x._674 = "674"
    x._675 = "675"
    x._676 = "676"
    x._677 = "677"
    x._678 = "678"
    x._679 = "679"
    x._680 = "680"
    x._681 = "681"
    x._682 = "682"
    x._683 = "683"
    x._684 = "684"
    x._685 = "685"
    x._686 = "686"
    x._687 = "687"
    x._688 = "688"
    x._689 = "689"
    x._690 = "690"
    x._691 = "691"
    x._692 = "692"
    x._693 = "693"
    x._694 = "694"
    x._695 = "695"
    x._696 = "696"
    x._697 = "697"
    x._698 = "698"
    x._699 = "699"
    x._700 = "700"
    x._701 = "701"
    x._702 = "702"
    x._703 = "703"
    x._704 = "704"
    x._705 = "705"
    x._706 = "706"
    x._707 = "707"
    x._708 = "708"
    x._709 = "709"
    x._710 = "710"
    x._711 = "711"
    x._712 = "712"
    x._713 = "713"
    x._714 = "714"
    x._715 = "715"
    x._716 = "716"
    x._717 = "717"
    x._718 = "718"
    x._719 = "719"
    x._720 = "720"
    x._721 = "721"
    x._722 = "722"
    x._723 = "723"
    x._724 = "724"
    x._725 = "725"
    x._726 = "726"
    x._727 = "727"
    x._728 = "728"
    x._729 = "729"
    x._730 = "730"
    x._731 = "731"
    x._732 = "732"
    x._733 = "733"
    x._734 = "734"
    x._735 = "735"
    x._736 = "736"
    x._737 = "737"
    x._738 = "738"
    x._739 = "739"
    x._740 = "740"
    x._741 = "741"
    x._742 = "742"
    x._743 = "743"
    x._744 = "744"
    x._745 = "745"
    x._746 = "746"
    x._747 = "747"
    x._748 = "748"
    x._749 = "749"
    x._750 = "750"
    x._751 = "751"
    x._752 = "752"
    x._753 = "753"
    x._754 = "754"
    x._755 = "755"
    x._756 = "756"
    x._757 = "757"
    x._758 = "758"
    x._759 = "759"
    x._760 = "760"
    x._761 = "761"
    x._762 = "762"
    x._763 = "763"
    x._764 = "764"
    x._765 = "765"
    x._766 = "766"
    x._767 = "767"
    x._768 = "768"
    x._769 = "769"
    x._770 = "770"
    x._771 = "771"
    x._772 = "772"
    x._773 = "773"
    x._774 = "774"
    x._775 = "775"
    x._776 = "776"
    x._777 = "777"
    x._778 = "778"
    x._779 = "779"
    x._780 = "780"
    x._781 = "781"
    x._782 = "782"
    x._783 = "783"
    x._784 = "784"
    x._785 = "785"
    x._786 = "786"
    x._787 = "787"
    x._788 = "788"
    x._789 = "789"
    x._790 = "790"
    x._791 = "791"
    x._792 = "792"
    x._793 = "793"
    x._794 = "794"
    x._795 = "795"
    x._796 = "796"
    x._797 = "797"
    x._798 = "798"
    x._799 = "799"
    x._800 = "800"
    x._801 = "801"
    x._802 = "802"
    x._803 = "803"
    x._804 = "804"
    x._805 = "805"
    x._806 = "806"
    x._807 = "807"
    x._808 = "808"
    x._809 = "809"
    x._810 = "810"
    x._811 = "811"
    x._812 = "812"
    x._813 = "813"
    x._814 = "814"
    x._815 = "815"
    x._816 = "816"
    x._817 = "817"
    x._818 = "818"
    x._819 = "819"
    x._820 = "820"
    x._821 = "821"
    x._822 = "822"
    x._823 = "823"
    x._824 = "824"
    x._825 = "825"
    x._826 = "826"
    x._827 = "827"
    x._828 = "828"
    x._829 = "829"
    x._830 = "830"
    x._831 = "831"
    x._832 = "832"
    x._833 = "833"
    x._834 = "834"
    x._835 = "835"
    x._836 = "836"
    x._837 = "837"
    x._838 = "838"
    x._839 = "839"
    x._840 = "840"
    x._841 = "841"
    x._842 = "842"
    x._843 = "843"
    x._844 = "844"
    x._845 = "845"
    x._846 = "846"
    x._847 = "847"
    x._848 = "848"
    x._849 = "849"
    x._850 = "850"
    x._851 = "851"
    x._852 = "852"
    x._853 = "853"
    x._854 = "854"
    x._855 = "855"
    x._856 = "856"
    x._857 = "857"
    x._858 = "858"
    x._859 = "859"
    x._860 = "860"
    x._861 = "861"
    x._862 = "862"
    x._863 = "863"
    x._864 = "864"
    x._865 = "865"
    x._866 = "866"
    x._867 = "867"
    x._868 = "868"
    x._869 = "869"
    x._870 = "870"
    x._871 = "871"
    x._872 = "872"
    x._873 = "873"
    x._874 = "874"
    x._875 = "875"
    x._876 = "876"
    x._877 = "877"
    x._878 = "878"
    x._879 = "879"
    x._880 = "880"
    x._881 = "881"
    x._882 = "882"
    x._883 = "883"
    x._884 = "884"
    x._885 = "885"
    x._886 = "886"
    x._887 = "887"
    x._888 = "888"
    x._889 = "889"
    x._890 = "890"
    x._891 = "891"
    x._892 = "892"
    x._893 = "893"
    x._894 = "894"
    x._895 = "895"
    x._896 = "896"
    x._897 = "897"
    x._898 = "898"
    x._899 = "899"
    x._900 = "900"
    x._901 = "901"
    x._902 = "902"
    x._903 = "903"
    x._904 = "904"
    x._905 = "905"
    x._906 = "906"
    x._907 = "907"
    x._908 = "908"
    x._909 = "909"
    x._910 = "910"
    x._911 = "911"
    x._912 = "912"
    x._913 = "913"
    x._914 = "914"
    x._915 = "915"
    x._916 = "916"
    x._917 = "917"
    x._918 = "918"
    x._919 = "919"
    x._920 = "920"
    x._921 = "921"
    x._922 = "922"
    x._923 = "923"
    x._924 = "924"
    x._925 = "925"
    x._926 = "926"
    x._927 = "927"
    x._928 = "928"
    x._929 = "929"
    x._930 = "930"
    x._931 = "931"
    x._932 = "932"
    x._933 = "933"
    x._934 = "934"
    x._935 = "935"
    x._936 = "936"
    x._937 = "937"
    x._938 = "938"
    x._939 = "939"
    x._940 = "940"
    x._941 = "941"
    x._942 = "942"
    x._943 = "943"
    x._944 = "944"
    x._945 = "945"
    x._946 = "946"
    x._947 = "947"
    x._948 = "948"
    x._949 = "949"
    x._950 = "950"
    x._951 = "951"
    x._952 = "952"
    x._953 = "953"
    x._954 = "954"
    x._955 = "955"
    x._956 = "956"
    x._957 = "957"
    x._958 = "958"
    x._959 = "959"
    x._960 = "960"
    x._961 = "961"
    x._962 = "962"
    x._963 = "963"
    x._964 = "964"
    x._965 = "965"
    x._966 = "966"
    x._967 = "967"
    x._968 = "968"
    x._969 = "969"
    x._970 = "970"
    x._971 = "971"
    x._972 = "972"
    x._973 = "973"
    x._974 = "974"
    x._975 = "975"
    x._976 = "976"
    x._977 = "977"
    x._978 = "978"
    x._979 = "979"
    x._980 = "980"
    x._981 = "981"
    x._982 = "982"
    x._983 = "983"
    x._984 = "984"
    x._985 = "985"
    x._986 = "986"
    x._987 = "987"
    x._988 = "988"
    x._989 = "989"
    x._990 = "990"
    x._991 = "991"
    x._992 = "992"
    x._993 = "993"
    x._994 = "994"
    x._995 = "995"
    x._996 = "996"
    x._997 = "997"
    x._998 = "998"
    x._999 = "999"
    x._1000 = "1000"
    x._1001 = "1001"
    x._1002 = "1002"
    x._1003 = "1003"
    x._1004 = "1004"
    x._1005 = "1005"
    x._1006 = "1006"
    x._1007 = "1007"
    x._1008 = "1008"
    x._1009 = "1009"
    x._1010 = "1010"
    x._1011 = "1011"
    x._1012 = "1012"
    x._1013 = "1013"
    x._1014 = "1014"
    x._1015 = "1015"
    x._1016 = "1016"
    x._1017 = "1017"
    x._1018 = "1018"
    x._1019 = "1019"
    x._1020 = "1020"
    x._1021 = "1021"
    x._1022 = "1022"
    x._1023 = "1023"
    x._1024 = "1024"
    x._1025 = "1025"
    x._1026 = "1026"
    x._1027 = "1027"
    x._1028 = "1028"
    x._1029 = "1029"
    x._1030 = "1030"
    x._1031 = "1031"
    x._1032 = "1032"
    x._1033 = "1033"
    x._1034 = "1034"
    x._1035 = "1035"
    x._1036 = "1036"
    x._1037 = "1037"
    x._1038 = "1038"
    x._1039 = "1039"
    x._1040 = "1040"
    x._1041 = "1041"
    x._1042 = "1042"
    x._1043 = "1043"
    x._1044 = "1044"
    x._1045 = "1045"
    x._1046 = "1046"
    x._1047 = "1047"
    x._1048 = "1048"
    x._1049 = "1049"
    x._1050 = "1050"
    x._1051 = "1051"
    x._1052 = "1052"
    x._1053 = "1053"
    x._1054 = "1054"
    x._1055 = "1055"
    x._1056 = "1056"
    x._1057 = "1057"
    x._1058 = "1058"
    x._1059 = "1059"
    x._1060 = "1060"
    x._1061 = "1061"
    x._1062 = "1062"
    x._1063 = "1063"
    x._1064 = "1064"
    x._1065 = "1065"
    x._1066 = "1066"
    x._1067 = "1067"
    x._1068 = "1068"
    x._1069 = "1069"
    x._1070 = "1070"
    x._1071 = "1071"
    x._1072 = "1072"
    x._1073 = "1073"
    x._1074 = "1074"
    x._1075 = "1075"
    x._1076 = "1076"
    x._1077 = "1077"
    x._1078 = "1078"
    x._1079 = "1079"
    x._1080 = "1080"
    x._1081 = "1081"
    x._1082 = "1082"
    x._1083 = "1083"
    x._1084 = "1084"
    x._1085 = "1085"
    x._1086 = "1086"
    x._1087 = "1087"
    x._1088 = "1088"
    x._1089 = "1089"
    x._1090 = "1090"
    x._1091 = "1091"
    x._1092 = "1092"
    x._1093 = "1093"
    x._1094 = "1094"
    x._1095 = "1095"
    x._1096 = "1096"
    x._1097 = "1097"
    x._1098 = "1098"
    x._1099 = "1099"
    x._1100 = "1100"
    x._1101 = "1101"
    x._1102 = "1102"
    x._1103 = "1103"
    x._1104 = "1104"
    x._1105 = "1105"
    x._1106 = "1106"
    x._1107 = "1107"
    x._1108 = "1108"
    x._1109 = "1109"
    x._1110 = "1110"
    x._1111 = "1111"
    x._1112 = "1112"
    x._1113 = "1113"
    x._1114 = "1114"
    x._1115 = "1115"
    x._1116 = "1116"
    x._1117 = "1117"
    x._1118 = "1118"
    x._1119 = "1119"
    x._1120 = "1120"
    x._1121 = "1121"
    x._1122 = "1122"
    x._1123 = "1123"
    x._1124 = "1124"
    x._1125 = "1125"
    x._1126 = "1126"
    x._1127 = "1127"
    x._1128 = "1128"
    x._1129 = "1129"
    x._1130 = "1130"
    x._1131 = "1131"
    x._1132 = "1132"
    x._1133 = "1133"
    x._1134 = "1134"
    x._1135 = "1135"
    x._1136 = "1136"
    x._1137 = "1137"
    x._1138 = "1138"
    x._1139 = "1139"
    x._1140 = "1140"
    x._1141 = "1141"
    x._1142 = "1142"
    x._1143 = "1143"
    x._1144 = "1144"
    x._1145 = "1145"
    x._1146 = "1146"
    x._1147 = "1147"
    x._1148 = "1148"
    x._1149 = "1149"
    x._1150 = "1150"
    x._1151 = "1151"
    x._1152 = "1152"
    x._1153 = "1153"
    x._1154 = "1154"
    x._1155 = "1155"
    x._1156 = "1156"
    x._1157 = "1157"
    x._1158 = "1158"
    x._1159 = "1159"
    x._1160 = "1160"
    x._1161 = "1161"
    x._1162 = "1162"
    x._1163 = "1163"
    x._1164 = "1164"
    x._1165 = "1165"
    x._1166 = "1166"
    x._1167 = "1167"
    x._1168 = "1168"
    x._1169 = "1169"
    x._1170 = "1170"
    x._1171 = "1171"
    x._1172 = "1172"
    x._1173 = "1173"
    x._1174 = "1174"
    x._1175 = "1175"
    x._1176 = "1176"
    x._1177 = "1177"
    x._1178 = "1178"
    x._1179 = "1179"
    x._1180 = "1180"
    x._1181 = "1181"
    x._1182 = "1182"
    x._1183 = "1183"
    x._1184 = "1184"
    x._1185 = "1185"
    x._1186 = "1186"
    x._1187 = "1187"
    x._1188 = "1188"
    x._1189 = "1189"
    x._1190 = "1190"
    x._1191 = "1191"
    x._1192 = "1192"
    x._1193 = "1193"
    x._1194 = "1194"
    x._1195 = "1195"
    x._1196 = "1196"
    x._1197 = "1197"
    x._1198 = "1198"
    x._1199 = "1199"
    x._1200 = "1200"
    x._1201 = "1201"
    x._1202 = "1202"
    x._1203 = "1203"
    x._1204 = "1204"
    x._1205 = "1205"
    x._1206 = "1206"
    x._1207 = "1207"
    x._1208 = "1208"
    x._1209 = "1209"
    x._1210 = "1210"
    x._1211 = "1211"
    x._1212 = "1212"
    x._1213 = "1213"
    x._1214 = "1214"
    x._1215 = "1215"
    x._1216 = "1216"
    x._1217 = "1217"
    x._1218 = "1218"
    x._1219 = "1219"
    x._1220 = "1220"
    x._1221 = "1221"
    x._1222 = "1222"
    x._1223 = "1223"
    x._1224 = "1224"
    x._1225 = "1225"
    x._1226 = "1226"
    x._1227 = "1227"
    x._1228 = "1228"
    x._1229 = "1229"
    x._1230 = "1230"
    x._1231 = "1231"
    x._1232 = "1232"
    x._1233 = "1233"
    x._1234 = "1234"
    x._1235 = "1235"
    x._1236 = "1236"
    x._1237 = "1237"
    x._1238 = "1238"
    x._1239 = "1239"
    x._1240 = "1240"
    x._1241 = "1241"
    x._1242 = "1242"
    x._1243 = "1243"
    x._1244 = "1244"
    x._1245 = "1245"
    x._1246 = "1246"
    x._1247 = "1247"
    x._1248 = "1248"
    x._1249 = "1249"
    x._1250 = "1250"
    x._1251 = "1251"
    x._1252 = "1252"
    x._1253 = "1253"
    x._1254 = "1254"
    x._1255 = "1255"
    x._1256 = "1256"
    x._1257 = "1257"
    x._1258 = "1258"
    x._1259 = "1259"
    x._1260 = "1260"
    x._1261 = "1261"
    x._1262 = "1262"
    x._1263 = "1263"
    x._1264 = "1264"
    x._1265 = "1265"
    x._1266 = "1266"
    x._1267 = "1267"
    x._1268 = "1268"
    x._1269 = "1269"
    x._1270 = "1270"
    x._1271 = "1271"
    x._1272 = "1272"
    x._1273 = "1273"
    x._1274 = "1274"
    x._1275 = "1275"
    x._1276 = "1276"
    x._1277 = "1277"
    x._1278 = "1278"
    x._1279 = "1279"
    x._1280 = "1280"
    x._1281 = "1281"
    x._1282 = "1282"
    x._1283 = "1283"
    x._1284 = "1284"
    x._1285 = "1285"
    x._1286 = "1286"
    x._1287 = "1287"
    x._1288 = "1288"
    x._1289 = "1289"
    x._1290 = "1290"
    x._1291 = "1291"
    x._1292 = "1292"
    x._1293 = "1293"
    x._1294 = "1294"
    x._1295 = "1295"
    x._1296 = "1296"
    x._1297 = "1297"
    x._1298 = "1298"
    x._1299 = "1299"
    x._1300 = "1300"
    x._1301 = "1301"
    x._1302 = "1302"
    x._1303 = "1303"
    x._1304 = "1304"
    x._1305 = "1305"
    x._1306 = "1306"
    x._1307 = "1307"
    x._1308 = "1308"
    x._1309 = "1309"
    x._1310 = "1310"
    x._1311 = "1311"
    x._1312 = "1312"
    x._1313 = "1313"
    x._1314 = "1314"
    x._1315 = "1315"
    x._1316 = "1316"
    x._1317 = "1317"
    x._1318 = "1318"
    x._1319 = "1319"
    x._1320 = "1320"
    x._1321 = "1321"
    x._1322 = "1322"
    x._1323 = "1323"
    x._1324 = "1324"
    x._1325 = "1325"
    x._1326 = "1326"
    x._1327 = "1327"
    x._1328 = "1328"
    x._1329 = "1329"
    x._1330 = "1330"
    x._1331 = "1331"
    x._1332 = "1332"
    x._1333 = "1333"
    x._1334 = "1334"
    x._1335 = "1335"
    x._1336 = "1336"
    x._1337 = "1337"
    x._1338 = "1338"
    x._1339 = "1339"
    x._1340 = "1340"
    x._1341 = "1341"
    x._1342 = "1342"
    x._1343 = "1343"
    x._1344 = "1344"
    x._1345 = "1345"
    x._1346 = "1346"
    x._1347 = "1347"
    x._1348 = "1348"
    x._1349 = "1349"
    x._1350 = "1350"
    x._1351 = "1351"
    x._1352 = "1352"
    x._1353 = "1353"
    x._1354 = "1354"
    x._1355 = "1355"
    x._1356 = "1356"
    x._1357 = "1357"
    x._1358 = "1358"
    x._1359 = "1359"
    x._1360 = "1360"
    x._1361 = "1361"
    x._1362 = "1362"
    x._1363 = "1363"
    x._1364 = "1364"
    x._1365 = "1365"
    x._1366 = "1366"
    x._1367 = "1367"
    x._1368 = "1368"
    x._1369 = "1369"
    x._1370 = "1370"
    x._1371 = "1371"
    x._1372 = "1372"
    x._1373 = "1373"
    x._1374 = "1374"
    x._1375 = "1375"
    x._1376 = "1376"
    x._1377 = "1377"
    x._1378 = "1378"
    x._1379 = "1379"
    x._1380 = "1380"
    x._1381 = "1381"
    x._1382 = "1382"
    x._1383 = "1383"
    x._1384 = "1384"
    x._1385 = "1385"
    x._1386 = "1386"
    x._1387 = "1387"
    x._1388 = "1388"
    x._1389 = "1389"
    x._1390 = "1390"
    x._1391 = "1391"
    x._1392 = "1392"
    x._1393 = "1393"
    x._1394 = "1394"
    x._1395 = "1395"
    x._1396 = "1396"
    x._1397 = "1397"
    x._1398 = "1398"
    x._1399 = "1399"
    x._1400 = "1400"
    x._1401 = "1401"
    x._1402 = "1402"
    x._1403 = "1403"
    x._1404 = "1404"
    x._1405 = "1405"
    x._1406 = "1406"
    x._1407 = "1407"
    x._1408 = "1408"
    x._1409 = "1409"
    x._1410 = "1410"
    x._1411 = "1411"
    x._1412 = "1412"
    x._1413 = "1413"
    x._1414 = "1414"
    x._1415 = "1415"
    x._1416 = "1416"
    x._1417 = "1417"
    x._1418 = "1418"
    x._1419 = "1419"
    x._1420 = "1420"
    x._1421 = "1421"
    x._1422 = "1422"
    x._1423 = "1423"
    x._1424 = "1424"
    x._1425 = "1425"
    x._1426 = "1426"
    x._1427 = "1427"
    x._1428 = "1428"
    x._1429 = "1429"
    x._1430 = "1430"
    x._1431 = "1431"
    x._1432 = "1432"
    x._1433 = "1433"
    x._1434 = "1434"
    x._1435 = "1435"
    x._1436 = "1436"
    x._1437 = "1437"
    x._1438 = "1438"
    x._1439 = "1439"
    x._1440 = "1440"
    x._1441 = "1441"
    x._1442 = "1442"
    x._1443 = "1443"
    x._1444 = "1444"
    x._1445 = "1445"
    x._1446 = "1446"
    x._1447 = "1447"
    x._1448 = "1448"
    x._1449 = "1449"
    x._1450 = "1450"
    x._1451 = "1451"
    x._1452 = "1452"
    x._1453 = "1453"
    x._1454 = "1454"
    x._1455 = "1455"
    x._1456 = "1456"
    x._1457 = "1457"
    x._1458 = "1458"
    x._1459 = "1459"
    x._1460 = "1460"
    x._1461 = "1461"
    x._1462 = "1462"
    x._1463 = "1463"
    x._1464 = "1464"
    x._1465 = "1465"
    x._1466 = "1466"
    x._1467 = "1467"
    x._1468 = "1468"
    x._1469 = "1469"
    x._1470 = "1470"
    x._1471 = "1471"
    x._1472 = "1472"
    x._1473 = "1473"
    x._1474 = "1474"
    x._1475 = "1475"
    x._1476 = "1476"
    x._1477 = "1477"
    x._1478 = "1478"
    x._1479 = "1479"
    x._1480 = "1480"
    x._1481 = "1481"
    x._1482 = "1482"
    x._1483 = "1483"
    x._1484 = "1484"
    x._1485 = "1485"
    x._1486 = "1486"
    x._1487 = "1487"
    x._1488 = "1488"
    x._1489 = "1489"
    x._1490 = "1490"
    x._1491 = "1491"
    x._1492 = "1492"
    x._1493 = "1493"
    x._1494 = "1494"
    x._1495 = "1495"
    x._1496 = "1496"
    x._1497 = "1497"
    x._1498 = "1498"
    x._1499 = "1499"
    x._1500 = "1500"

    x
  }
}
